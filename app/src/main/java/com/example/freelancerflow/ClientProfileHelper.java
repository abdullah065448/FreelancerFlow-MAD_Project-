package com.example.freelancerflow;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.*;

public class ClientProfileHelper {

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final String COLLECTION = "users/clients";

    public void createClientProfile(ClientModel client,
                                    OnCompleteListener<Void> listener) {
        db.collection(COLLECTION)
                .document(client.getClientId())
                .set(client)
                .addOnCompleteListener(listener);
    }

    public void getClientProfile(String clientId,
                                 OnCompleteListener<DocumentSnapshot> listener) {
        db.collection(COLLECTION)
                .document(clientId)
                .get()
                .addOnCompleteListener(listener);
    }

    public void updateClientProfile(String clientId,
                                    String name,
                                    String companyName,
                                    String phoneNumber,
                                    String country,
                                    String profileImageUrl,
                                    OnCompleteListener<Void> listener) {

        db.collection(COLLECTION)
                .document(clientId)
                .update(
                        "name", name,
                        "companyName", companyName,
                        "phoneNumber", phoneNumber,
                        "country", country,
                        "profileImageUrl", profileImageUrl
                )
                .addOnCompleteListener(listener);
    }

    public void deleteClientProfile(String clientId,
                                    OnCompleteListener<Void> listener) {
        db.collection(COLLECTION)
                .document(clientId)
                .delete()
                .addOnCompleteListener(listener);
    }
}
