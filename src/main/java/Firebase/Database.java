package Firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;

class Database {
    private final String PRIVATEKEYLOCATION = "src/main/resources/FireBaseKey.json";
    private final String DATABASEURL = "https://small-world-462b7.firebaseio.com";
    private Firestore db;

    Database() {
        try(FileInputStream serviceAccount = new FileInputStream(PRIVATEKEYLOCATION)){
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl(DATABASEURL)
                    .build();

            FirebaseApp.initializeApp(options);
            this.db = FirestoreClient.getFirestore();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Firestore getFirestoreDatabase() {
        return this.db;
    }

}
