package java16.db;

public class Generator {
    private static Long hospitalId = 1L;
    private static Long doctorId = 1L;
    private static Long departmentId = 1L;
    private static Long patientId = 1L;

    public static Long setHospitalId() {
        return hospitalId++;
    }

    public static Long setDepartmentId() {
        return departmentId++;
    }

    public static Long setDoctorId() {
        return doctorId++;
    }

    public static Long setPatientId() {
        return patientId++;
    }


}
