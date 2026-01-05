package com.example.freelancerflow;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.*;

public class AdminProfileHelper {

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final String COLLECTION = "users/admins";

    public void createAdminProfile(AdminModel admin,
                                   OnCompleteListener<Void> listener) {
        db.collection(COLLECTION)
                .document(admin.getAdminId())
                .set(admin)
                .addOnCompleteListener(listener);
    }

    public void getAdminProfile(String adminId,
                                OnCompleteListener<DocumentSnapshot> listener) {
        db.collection(COLLECTION)
                .document(adminId)
                .get()
                .addOnCompleteListener(listener);
    }

    public void updateAdminProfile(String adminId,
                                   String name,
                                   String phoneNumber,
                                   OnCompleteListener<Void> listener) {

        db.collection(COLLECTION)
                .document(adminId)
                .update(
                        "name", name,
                        "phoneNumber", phoneNumber
                )
                .addOnCompleteListener(listener);
    }

    public void updateLastLogin(String adminId,
                                long lastLogin,
                                OnCompleteListener<Void> listener) {
        db.collection(COLLECTION)
                .document(adminId)
                .update("lastLogin", lastLogin)
                .addOnCompleteListener(listener);
    }

    public void deleteAdminProfile(String adminId,
                                   OnCompleteListener<Void> listener) {
        db.collection(COLLECTION)
                .document(adminId)
                .delete()
                .addOnCompleteListener(listener);
    }
}
