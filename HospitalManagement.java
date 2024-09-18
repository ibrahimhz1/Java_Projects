package praPractices;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HospitalManagement {
	public static void main(String[] args) throws ParseException {
		Scanner sc = new Scanner(System.in);
		ArrayList<Hospital> hospitalsArr = new ArrayList<>();
		int numHospitals = sc.nextInt();sc.nextLine();
		for (int i = 0; i < numHospitals; i++) {
            String hospitalName = sc.nextLine();
            int numDoctors = sc.nextInt();sc.nextLine();
            ArrayList<Doctor> doctorArr = new ArrayList<>();
            for (int j = 0; j < numDoctors; j++) {
                int doctorId = sc.nextInt();sc.nextLine();
                String doctorName = sc.nextLine();
                String specialization = sc.nextLine();
                int yearsOfExperience = sc.nextInt();sc.nextLine();
                int numPatients = sc.nextInt();sc.nextLine();
                ArrayList<Patient> patientArr = new ArrayList<>();
                for (int k = 0; k < numPatients; k++) {
                    int patientId = sc.nextInt();sc.nextLine();
                    String patientName = sc.nextLine();
                    int age = sc.nextInt();sc.nextLine();
                    String disease = sc.nextLine();
                    String admissionDate = sc.nextLine();
                    Patient patient = new Patient(patientId, patientName, age, disease, admissionDate);
                    patientArr.add(patient);
                }
                Doctor doctor = new Doctor(doctorId, doctorName, specialization, yearsOfExperience, patientArr);
                doctorArr.add(doctor);
            }
            Hospital hospital = new Hospital(hospitalName, doctorArr);
            hospitalsArr.add(hospital);
        }
		ArrayList<String> freqDiseases = Solution.findMostFrequentDisease(hospitalsArr);
		System.out.println("###############");
		System.out.println("Disease Frequency: ");
		for(String dis : freqDiseases) {
			System.out.println(dis);
		}
		System.out.println("###############");
		sc.close();
	}
}

class Solution{
	public static ArrayList<String> findMostFrequentDisease(ArrayList<Hospital> hospitalArr) throws ParseException {
		ArrayList<String> highestFreq = new ArrayList<>();
		ArrayList<Patient> patientArr = null;
		for(Hospital h : hospitalArr) {
			int maxDCount = Integer.MIN_VALUE;
			HashMap<String, Integer> diseaseMap = new HashMap<>();
			ArrayList<String> maxDiseases = new ArrayList<>();
			for(Doctor d : h.getHosDoctors()) {
				patientArr = d.getDocPatients();
				for(Patient p : d.getDocPatients()) {
					diseaseMap.put(p.getDisease(), diseaseMap.getOrDefault(p.getDisease(),0)+1);
					if(diseaseMap.get(p.getDisease()) > maxDCount) maxDCount = diseaseMap.get(p.getDisease());
				}
			}
			for(String key : diseaseMap.keySet()) {
				if(maxDCount == diseaseMap.get(key)) maxDiseases.add(key);
			}
			String ans = "";
			String maxDate = "2025-12-31";
			for(Patient p : patientArr) {
				for(String maxDisease : maxDiseases) {
					if(p.getDisease().equals(maxDisease)) {
						SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
						Date date1 = sd.parse(maxDate);
						Date date2 = sd.parse(p.getAdmissionDate());
						if(date1.compareTo(date2) > 0) {
							maxDate = p.getAdmissionDate();
							ans = p.getDisease();
						}
					}
				}
			}
			highestFreq.add(ans);
		}
		return highestFreq;
	}
}

class Patient{
	private int patientID;
	private String name;
	private int age;
	private String disease;
	private String admissionDate;
	
	public Patient(int patientID, String name, int age, String disease, String admissionDate) {
		this.patientID = patientID;
		this.name = name;
		this.age = age;
		this.disease = disease;
		this.admissionDate = admissionDate;
	}
	
	public int getPatientID() {return this.patientID;}
	public String getPatientName() { return this.name;}
	public int getAge() {return this.age;}
	public String getDisease() {return this.disease;}
	public String getAdmissionDate() {return this.admissionDate;}
	
}

class Doctor{
	private int doctorID;
	private String name;
	private String specialization;
	private int yearsOfExp;
	public ArrayList<Patient> patientArr;
	
	public Doctor(int doctorID, String name, String specs, int exp, ArrayList<Patient> patientArr) {
		this.doctorID = doctorID;
		this.name = name;
		this.specialization = specs;
		this.yearsOfExp = exp;
		this.patientArr = patientArr;
	}
	
	public int getDoctorID() {return this.doctorID;}
	public String getDoctorName() {return this.name;}
	public String getDoctorSpecs() {return this.specialization;}
	public int getDoctorExp() {return this.yearsOfExp;}
	public ArrayList<Patient> getDocPatients() {return this.patientArr;}
	
}

class Hospital{
	private String hospitalName;
	public ArrayList<Doctor> doctorArr;
	
	public Hospital(String hospitalName, ArrayList<Doctor> doctorArr) {
		this.hospitalName = hospitalName;
		this.doctorArr = doctorArr;
	}
	
	public String getHosName() {return this.hospitalName;}
	public ArrayList<Doctor> getHosDoctors(){return this.doctorArr;}
}
