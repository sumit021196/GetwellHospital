import java.util.Map;
import java.util.HashMap;

// Step 1: Create an interface for repository
interface PatientRepository {
    void save(Patient patient);
    Patient findById(int id);
}

// Step 2: Create a class for patient data (SRP)
class Patient {
    private int id;
    private String name;
    private int age;
    private String contactInfo;

    public Patient(int id, String name, int age, String contactInfo) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.contactInfo = contactInfo;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    @Override
    public String toString() {
        return "Patient ID: " + id + ", Name: " + name + ", Age: " + age + ", Contact: " + contactInfo;
    }
}

// Step 3: Implement the repository interface (SRP & OCP)
class InMemoryPatientRepository implements PatientRepository {
    private Map<Integer, Patient> patientMap = new HashMap<>();

    @Override
    public void save(Patient patient) {
        patientMap.put(patient.getId(), patient);
        System.out.println("Patient registered successfully!");
    }

    @Override
    public Patient findById(int id) {
        return patientMap.get(id);
    }
}

// Step 4: Create a class for patient management (SRP)
class PatientManagement {
    private PatientRepository repository;

    // Dependency Injection (DIP)
    public PatientManagement(PatientRepository repository) {
        this.repository = repository;
    }

    public void registerPatient(Patient patient) {
        repository.save(patient);
    }

    public void viewPatient(int id) {
        Patient patient = repository.findById(id);
        if (patient != null) {
            System.out.println(patient);
        } else {
            System.out.println("Patient not found!");
        }
    }
}

// Step 5: Main class to run the application
public class Main {
    public static void main(String[] args) {
        // Use Dependency Injection to provide the repository (DIP)
        PatientRepository repository = new InMemoryPatientRepository();
        PatientManagement management = new PatientManagement(repository);

        // Register a patient
        Patient patient1 = new Patient(1, "John Doe", 30, "1234567890");
        management.registerPatient(patient1);

        // View patient details
        management.viewPatient(1);
    }
}
