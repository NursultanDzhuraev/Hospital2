package java16;


import java16.db.Generator;
import java16.enam.Gender;
import java16.models.Department;
import java16.models.Doctor;
import java16.models.Hospital;
import java16.models.Patient;
import java16.service.impl.DepartmentServiceImpl;
import java16.service.impl.DoctorServiceImpl;
import java16.service.impl.HospitalServiceImpl;
import java16.service.impl.PatientServiceImpl;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanForStr = new Scanner(System.in);
    static Scanner scanForNumber = new Scanner(System.in);

    public static void main(String[] args) {
        HospitalServiceImpl hospitalService = new HospitalServiceImpl();
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl();
        DoctorServiceImpl doctorService = new DoctorServiceImpl();
        PatientServiceImpl patientService = new PatientServiceImpl();

        while (true) {
            System.out.println("""
                    *** Бир команда танданыз ***
                            0 Exit
                            1 Hospital
                            2 Department
                            3 Doctor
                            4 Patient
                    """);
            switch (checkValidCommand()) {
                case 0 -> {
                    System.out.println("Good bye!");
                    return;
                }
                case 1 -> hospitalMenu(hospitalService);
                case 2 -> departmentMenu(departmentService);
                case 3 -> doctorMenu(doctorService);
                case 4 -> patientMenu(patientService);
            }
        }


    }

    public static Patient createPatientInput() {
        Patient patient = new Patient();
        System.out.print("Enter patient first name: ");
        patient.setFirstName(scanForStr.nextLine());
        System.out.print("Enter patient last name: ");
        patient.setLastName(scanForStr.nextLine());
        System.out.print("Enter patient age: ");
        patient.setAge(scanForNumber.nextInt());
        patient.setId(Generator.setPatientId());
        while (true) {
            System.out.print("Enter doctor gender: ");
            String input = scanForStr.nextLine().toUpperCase();
            try {
                patient.setGender(Gender.valueOf(input));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("invalid gender. Enter valid gender:");
            }
        }
        return patient;
    }

    public static Hospital createHospitalInput() {
        Hospital hospital = new Hospital();

        System.out.print("Enter hospital name: ");
        hospital.setHospitalName(scanForStr.nextLine());
        System.out.print("Enter hospital address: ");
        hospital.setAddress(scanForStr.nextLine());
        hospital.setDepartments(new ArrayList<>());
        hospital.setDoctors(new ArrayList<>());
        hospital.setPatients(new ArrayList<>());
        hospital.setId(Generator.setHospitalId());
        return hospital;
    }

    public static Department createDepartmentInput() {
        Department department = new Department();
        System.out.print("Enter department name: ");
        department.setDepartmentName(scanForStr.nextLine());
        department.setId(Generator.setDepartmentId());
        department.setDoctors(new ArrayList<>());
        return department;
    }

    public static Doctor createDoctorInput() {
        Doctor doctor = new Doctor();
        doctor.setId(Generator.setDoctorId());
        System.out.print("Enter doctor first name: ");
        doctor.setFirstName(scanForStr.nextLine());
        System.out.print("Enter doctor last name: ");
        doctor.setLastName(scanForStr.nextLine());
        System.out.print("Enter doctor experience year: ");
        try {
            doctor.setExperienceYear(scanForNumber.nextInt());
        } catch (InputMismatchException e) {
            System.out.println("san jaz");
        }
        while (true) {
            System.out.print("Enter doctor gender: ");
            String input = scanForStr.nextLine().toUpperCase();
            try {
                doctor.setGender(Gender.valueOf(input));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("invalid gender. Enter valid gender:");
            }
        }
        return doctor;
    }

    public static int checkValidCommand() {
        System.out.print("Command: ");
        while (!scanForNumber.hasNextInt()) {
            System.out.print("Invalid command. Enter valid command:");
            scanForNumber.next();
        }
        return scanForNumber.nextInt();
    }

    public static void patientMenu(PatientServiceImpl patientService) {
        while (true) {
            System.out.println("""
                    *** Бир команда танданыз ***
                            0 exit
                            1 add patient
                            2 remove by Id
                            3 update by Id
                            4 add patients to hospital
                            5 get patient by Id
                            6 get patient by Age
                            7 sort patients by Age
                    """);
            switch (checkValidCommand()) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    System.out.print("Enter hospital id: ");
                    try {
                        System.out.println(patientService.add(scanForNumber.nextLong(), createPatientInput()));
                    } catch (InputMismatchException e) {
                        System.out.println("san jaz");
                    }
                }
                case 2 -> {
                    System.out.print("Enter patient id: ");
                    try {
                        patientService.removeById(scanForNumber.nextLong());
                    } catch (InputMismatchException e) {
                        System.out.println("san jaz");
                    }
                }
                case 3 -> {
                    System.out.print("Enter patient id: ");
                    try {
                        System.out.println(patientService.updateById(scanForNumber.nextLong(), createPatientInput()));
                    } catch (InputMismatchException e) {
                        System.out.println("san jaz");
                    }
                }
                case 4 -> {

                    Long hospitalId = null;

                    System.out.print("Канча patient кошууну каалайсын? ");
                    int a = scanForNumber.nextInt();
                    List<Patient> patientInput = new ArrayList<>();
                    while (a >0) {
                         patientInput.add(createPatientInput());
                        a--;
                    }
                    System.out.print("Кошууну каалаган hospital id: ");
                    try {
                        hospitalId = scanForNumber.nextLong();
                    } catch (InputMismatchException e) {
                        System.out.println("san jaz");
                    }

                    System.out.println(patientService.addPatientsToHospital(hospitalId, patientInput));
                }
                case 5 -> {
                    System.out.print("Enter patient id: ");
                    try {
                        System.out.println(patientService.getPatientById(scanForNumber.nextLong()));
                    } catch (InputMismatchException e) {
                        System.out.println("san jaz");
                    }
                }
                case 6 -> System.out.println(patientService.getPatientByAge());

                case 7 -> {
                    System.out.print("Enter asc Or desc: ");
                    System.out.println(patientService.sortPatientsByAge(scanForStr.nextLine()));
                }
            }
        }
    }

    public static void doctorMenu(DoctorServiceImpl doctorService) {
        while (true) {
            System.out.println("""
                    *** Бир команда танданыз ***
                            0 exit
                            1 add doctor
                            2 remove by Id
                            3 update by Id
                            4 find doctor by Id
                            5 assign doctor to department
                            6 get all doctors by hospital Id
                            7 get all doctors by department Id
                    """);
            switch (checkValidCommand()) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    System.out.print("Enter hospital id: ");
                    try {
                        System.out.println(doctorService.add(scanForNumber.nextLong(), createDoctorInput()));
                    } catch (InputMismatchException e) {
                        System.out.println("san jaz");
                    }
                }
                case 2 -> {
                    System.out.print("Enter doctor id: ");
                    try {
                        doctorService.removeById(scanForNumber.nextLong());
                    } catch (InputMismatchException e) {
                        System.out.println("san jaz");
                    }
                }
                case 3 -> {
                    System.out.print("Enter doctor id: ");
                    try {
                        System.out.println(doctorService.updateById(scanForNumber.nextLong(), createDoctorInput()));
                    } catch (InputMismatchException e) {
                        System.out.println("san jaz");
                    }
                }
                case 4 -> {
                    System.out.print("Enter doctor id: ");
                    try {
                        System.out.println(doctorService.findDoctorById(scanForNumber.nextLong()));
                    } catch (InputMismatchException e) {
                        System.out.println("san jaz");
                    }
                }
                case 5 -> {
                    System.out.print("Enter department id: ");
                    Long departmentId = null;
                    try {
                        departmentId = scanForNumber.nextLong();
                    } catch (InputMismatchException e) {
                        System.out.println("san jaz");
                    }
                    System.out.print("Enter doctors id:");
                    doctorService.assignDoctorToDepartment(departmentId, List.of(scanForNumber.nextLong()));

                }
                case 6 -> {
                    System.out.print("Enter hospital id: ");
                    try {
                        System.out.println(doctorService.getAllDoctorsByHospitalId(scanForNumber.nextLong()));
                    } catch (InputMismatchException e) {
                        System.out.println("san jaz");
                    }
                }
                case 7 -> {
                    System.out.print("Enter department id: ");
                    try {
                        System.out.println(doctorService.getAllDoctorsByDepartmentId(scanForNumber.nextLong()));
                    } catch (InputMismatchException e) {
                        System.out.println("san jaz");
                    }
                }
            }
        }

    }

    public static void departmentMenu(DepartmentServiceImpl departmentService) {
        while (true) {

            System.out.println("""
                    *** Бир команда танданыз ***
                            0 exit
                            1 add department
                            2 remove by Id
                            3 update by Id
                            4 get all department by hospital
                            5 find department by name
                    """);
            switch (checkValidCommand()) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    System.out.print("Enter hospital id: ");
                    try {
                        System.out.println(departmentService.add(scanForNumber.nextLong(), createDepartmentInput()));
                    } catch (InputMismatchException e) {
                        System.out.println("san jaz");
                    }
                }
                case 2 -> {
                    System.out.print("Enter department id: ");
                    try {
                        departmentService.removeById(scanForNumber.nextLong());
                    } catch (InputMismatchException e) {
                        System.out.println("san jaz");
                    }
                }
                case 3 -> {
                    System.out.print("Enter department id: ");
                    try {
                        System.out.println(departmentService.updateById(scanForNumber.nextLong(), createDepartmentInput()));
                    } catch (InputMismatchException e) {
                        System.out.println("san jaz");
                    }
                }
                case 4 -> {
                    System.out.print("Enter hospital id: ");
                    try {
                        System.out.println(departmentService.getAllDepartmentByHospital(scanForNumber.nextLong()));
                    } catch (InputMismatchException e) {
                        System.out.println("san jaz");
                    }
                }
                case 5 -> {
                    System.out.print("Enter department name: ");
                    System.out.println(departmentService.findDepartmentByName(scanForStr.nextLine()));
                }
                default -> System.out.println("invalid command!");
            }
        }
    }

    public static void hospitalMenu(HospitalServiceImpl hospitalService) {
        while (true) {
            System.out.println("""
                    *** Бир команда танданыз ***
                            0 exit
                            1 add Hospital
                            2 find Hospital by Id
                            3 get all Hospital
                            4 get all patient from Hospital
                            5 delete Hospital by Id
                            6 get all Hospital by address
                    """);
            switch (checkValidCommand()) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    System.out.println(hospitalService.addHospital(createHospitalInput()));
                }
                case 2 -> {
                    System.out.print("Enter hospital id: ");
                    try {
                        System.out.println(hospitalService.findHospitalById(scanForNumber.nextLong()));
                    } catch (InputMismatchException e) {
                        System.out.println("san jaz");
                    }
                }
                case 3 -> System.out.println(hospitalService.getAllHospital());

                case 4 -> {
                    System.out.print("Enter hospital id: ");
                    try {
                        System.out.println(hospitalService.getAllPatientFromHospital(scanForNumber.nextLong()));
                    } catch (InputMismatchException e) {
                        System.out.println("san jaz");
                    }
                }
                case 5 -> {
                    System.out.print("Enter hospital id: ");
                    try {
                        System.out.println(hospitalService.deleteHospitalById(scanForNumber.nextLong()));
                    } catch (InputMismatchException e) {
                        System.out.println("san jaz");
                    }
                }
                case 6 -> {
                    System.out.print("Enter hospital address: ");
                    System.out.println(hospitalService.getAllHospitalByAddress(scanForStr.nextLine()));
                }
                default -> System.out.println("invalid command!");
            }
        }
    }

}