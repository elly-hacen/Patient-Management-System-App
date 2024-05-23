package com.example.covidmanagementsystem;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class AddPatientActivity extends AppCompatActivity {

    private EditText editTextName, editTextAddress, editTextProvince, editTextPhone;
    private ImageView imageViewPicture;
    private Spinner spinnerMedicalStatus;
    private Button buttonUploadPicture, buttonAddPatient;
    private Uri imageUri;
    private DatabaseReference databasePatients;
    private StorageReference storagePatientsPictures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        // Initialize UI components
        editTextName = findViewById(R.id.editTextName);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextProvince = findViewById(R.id.editTextProvince);
        editTextPhone = findViewById(R.id.editTextPhone);
        imageViewPicture = findViewById(R.id.imageViewPicture);
        spinnerMedicalStatus = findViewById(R.id.spinnerMedicalStatus);
        buttonUploadPicture = findViewById(R.id.buttonUploadPicture);
        buttonAddPatient = findViewById(R.id.buttonAddPatient);

        // Initialize Firebase references
        databasePatients = FirebaseDatabase.getInstance().getReference("patients");
        storagePatientsPictures = FirebaseStorage.getInstance().getReference("patient_pictures");

        // Set up spinner adapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.medical_status_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMedicalStatus.setAdapter(adapter);

        // Set click listeners
        buttonUploadPicture.setOnClickListener(v -> uploadPicture());
        buttonAddPatient.setOnClickListener(v -> addPatient());
    }

    private void uploadPicture() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            imageViewPicture.setImageURI(imageUri);
        }
    }

    private void addPatient() {
        // Retrieve input data
        String name = editTextName.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        String province = editTextProvince.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String medicalStatus = spinnerMedicalStatus.getSelectedItem().toString();
        String patientId = databasePatients.push().getKey();

        // Validate inputs
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(address) || TextUtils.isEmpty(province) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(medicalStatus)) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (imageUri != null) {
            StorageReference fileReference = storagePatientsPictures.child(patientId + ".jpg");
            fileReference.putFile(imageUri).addOnSuccessListener(taskSnapshot -> {
                fileReference.getDownloadUrl().addOnSuccessListener(uri -> {
                    String pictureUrl = uri.toString();
                    Patient patient = new Patient(name, address, province, phone, pictureUrl, medicalStatus, patientId);
                    savePatientToDatabase(patient);
                }).addOnFailureListener(e -> showToast("Failed to get picture URL"));
            }).addOnFailureListener(e -> showToast("Failed to upload picture"));
        } else {
            Patient patient = new Patient(name, address, province, phone, null, medicalStatus, patientId);
            savePatientToDatabase(patient);
        }
    }

    private void savePatientToDatabase(Patient patient) {
        databasePatients.child(patient.getPatientId()).setValue(patient)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        showToast("Patient data added successfully");
                        createPdf(patient);
                    } else {
                        showToast("Failed to add patient data");
                    }
                });
    }

    private void createPdf(Patient patient) {
        // Logic to create PDF file and upload it to Firebase
        // This part will require using a PDF library like iText or Android's PDFDocument
        // After creating the PDF, upload it to Firebase Storage and update the patient record
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
