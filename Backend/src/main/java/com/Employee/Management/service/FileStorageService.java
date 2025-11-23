package com.Employee.Management.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.Employee.Management.entity.PayslipRecord;

@Service
public class FileStorageService {

    private final Path storageDir;

    public FileStorageService(@Value("${app.payslip-dir}") String storagePath) {
        this.storageDir = Paths.get(storagePath).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.storageDir);   // auto-create folder
        } catch (Exception e) {
            throw new RuntimeException("Could not create payslip directory: " + e.getMessage());
        }
    }

    // Save the file and return filename
    public String storeFile(Long employeeId, String monthYear, byte[] fileBytes) {
        try {
            String safeFileName = employeeId + "_" + monthYear + "_" + System.currentTimeMillis() + ".pdf";

            Path target = this.storageDir.resolve(safeFileName);

            Files.write(target, fileBytes, StandardOpenOption.CREATE);

            return target.toString();
        } catch (IOException e) {
            throw new RuntimeException("Could not store file: " + e.getMessage());
        }
    }

    // Load the file path for download
    public Path loadPath(PayslipRecord rec) {
        Path path = Paths.get(rec.getFilename());

        if (!Files.exists(path)) {
            throw new RuntimeException("Stored file not found on disk: " + path);
        }

        return path;
    }
}
