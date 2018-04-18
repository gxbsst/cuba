package com.vtstar.sct.web.vtapps;

import com.haulmont.cuba.core.app.FileStorageService;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.FileStorageException;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.Embedded;
import com.haulmont.cuba.gui.components.FileUploadField;
import com.haulmont.cuba.gui.data.DataSupplier;
import com.haulmont.cuba.gui.export.ExportDisplay;
import com.haulmont.cuba.gui.export.ExportFormat;
import com.haulmont.cuba.gui.upload.FileUploadingAPI;

import com.vtstar.sct.entity.VtApps;
import org.slf4j.Logger;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

public class VtAppsEdit extends AbstractEditor<VtApps> {

    @Inject
    private FileStorageService fileStorageService;
    @Inject
    private FileUploadField uploadField;
    @Inject
    private FileUploadingAPI fileUploadingAPI;
    @Inject
    private DataSupplier dataSupplier;

    @Inject
    private Embedded embeddedImage;
    @Inject
    private Button clearImageBtn;
    @Inject
    private Button downloadImageBtn;

    @Inject
    private ExportDisplay exportDisplay;

    private static final int IMG_HEIGHT = 190;
    private static final int IMG_WIDTH = 220;

    @Override
    public void init(Map<String, Object> params) {
        uploadField.addFileUploadSucceedListener(event -> {
            FileDescriptor fd = uploadField.getFileDescriptor();
            try {
                // save file to FileStorage
                fileUploadingAPI.putFileIntoStorage(uploadField.getFileId(), fd);
            } catch (FileStorageException e) {
                throw new RuntimeException("Error saving file to FileStorage", e);
            }
            // save file descriptor to database
            FileDescriptor commitedFd = dataSupplier.commit(fd);
            getItem().setLogo(commitedFd);
            displayImage();
            showNotification("Uploaded file: " + uploadField.getFileName(), NotificationType.HUMANIZED);
        });

        uploadField.addFileUploadErrorListener(event ->
                showNotification("File upload error", NotificationType.HUMANIZED));
    }

    @Override
    protected void postInit() {
        displayImage();
        updateImageButtons(getItem().getLogo() != null);
    }

    @Inject
    private Logger log;

    private void updateImageButtons(boolean enable) {
        downloadImageBtn.setEnabled(enable);
        clearImageBtn.setEnabled(enable);
    }



    public void onDownloadImageBtnClick() {
        if (getItem().getLogo() != null) {
            exportDisplay.show(getItem().getLogo(), ExportFormat.OCTET_STREAM);
        }

    }

    public void onClearImageBtnClick() {
        getItem().setLogo(null);
        displayImage();
    }


    private void displayImage() {
        byte[] bytes = null;
        if (getItem().getLogo() != null) {
            try {
                bytes = fileStorageService.loadFile(getItem().getLogo());
            } catch (FileStorageException e) {
                showNotification("Unable to load image file", NotificationType.HUMANIZED);
            }
        }
        if (bytes != null) {
            embeddedImage.setSource(getItem().getLogo().getName(), new ByteArrayInputStream(bytes));
            embeddedImage.setType(Embedded.Type.IMAGE);
            BufferedImage image;
            try {
                image = ImageIO.read(new ByteArrayInputStream(bytes));
                int width = image.getWidth();
                int height = image.getHeight();

                if (((double) height / (double) width) > ((double) IMG_HEIGHT / (double) IMG_WIDTH)) {
                    embeddedImage.setHeight(String.valueOf(IMG_HEIGHT));
                    embeddedImage.setWidth(String.valueOf(width * IMG_HEIGHT / height));
                } else {
                    embeddedImage.setWidth(String.valueOf(IMG_WIDTH));
                    embeddedImage.setHeight(String.valueOf(height * IMG_WIDTH / width));
                }
            } catch (IOException e) {

                log.error("Unable to resize image", e);
            }
            // refresh image
            embeddedImage.setVisible(false);
            embeddedImage.setVisible(true);
        } else {
            embeddedImage.setVisible(false);
        }
    }
}