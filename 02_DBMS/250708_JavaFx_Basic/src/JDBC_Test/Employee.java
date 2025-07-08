package JDBC_Test;

import java.io.Serializable;
import java.sql.Date;

public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;
	private int EMPLOYEE_ID;
	private String FIRST_NAME;
	private String LAST_NAME;
	private String EMAIL;
	private String PHONE_NUMBER;
	private Date HIRE_DATE;
	private String JOB_ID;
	private float SALARY;
	private float COMMISSION_PCT;
	private String MANAGER_ID;
	private int DEPARTMENT_ID;

	protected int getEMPLOYEE_ID() {
		return EMPLOYEE_ID;
	}

	protected void setEMPLOYEE_ID(int eMPLOYEE_ID) {
		EMPLOYEE_ID = eMPLOYEE_ID;
	}

	protected String getFIRST_NAME() {
		return FIRST_NAME;
	}

	protected void setFIRST_NAME(String fIRST_NAME) {
		FIRST_NAME = fIRST_NAME;
	}

	protected String getLAST_NAME() {
		return LAST_NAME;
	}

	protected void setLAST_NAME(String lAST_NAME) {
		LAST_NAME = lAST_NAME;
	}

	protected String getEMAIL() {
		return EMAIL;
	}

	protected void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	protected String getPHONE_NUMBER() {
		return PHONE_NUMBER;
	}

	protected void setPHONE_NUMBER(String pHONE_NUMBER) {
		PHONE_NUMBER = pHONE_NUMBER;
	}

	protected Date getHIRE_DATE() {
		return HIRE_DATE;
	}

	protected void setHIRE_DATE(Date hIRE_DATE) {
		HIRE_DATE = hIRE_DATE;
	}

	protected String getJOB_ID() {
		return JOB_ID;
	}

	protected void setJOB_ID(String jOB_ID) {
		JOB_ID = jOB_ID;
	}

	protected float getSALARY() {
		return SALARY;
	}

	protected void setSALARY(float sALARY) {
		SALARY = sALARY;
	}

	protected float getCOMMISSION_PCT() {
		return COMMISSION_PCT;
	}

	protected void setCOMMISSION_PCT(float cOMMISSION_PCT) {
		COMMISSION_PCT = cOMMISSION_PCT;
	}

	protected String getMANAGER_ID() {
		return MANAGER_ID;
	}

	protected void setMANAGER_ID(String mANAGER_ID) {
		MANAGER_ID = mANAGER_ID;
	}

	protected int getDEPARTMENT_ID() {
		return DEPARTMENT_ID;
	}

	protected void setDEPARTMENT_ID(int dEPARTMENT_ID) {
		DEPARTMENT_ID = dEPARTMENT_ID;
	}
	public Employee() {};
	public Employee(int eMPLOYEE_ID, String fIRST_NAME, String lAST_NAME, String eMAIL, String pHONE_NUMBER,
			Date hIRE_DATE, String jOB_ID, float sALARY, float cOMMISSION_PCT, String mANAGER_ID, int dEPARTMENT_ID) {
		super();
		EMPLOYEE_ID = eMPLOYEE_ID;
		FIRST_NAME = fIRST_NAME;
		LAST_NAME = lAST_NAME;
		EMAIL = eMAIL;
		PHONE_NUMBER = pHONE_NUMBER;
		HIRE_DATE = hIRE_DATE;
		JOB_ID = jOB_ID;
		SALARY = sALARY;
		COMMISSION_PCT = cOMMISSION_PCT;
		MANAGER_ID = mANAGER_ID;
		DEPARTMENT_ID = dEPARTMENT_ID;
	}

	@Override
	public String toString() {
		return "VOEmployee [EMPLOYEE_ID=" + EMPLOYEE_ID + ", FIRST_NAME=" + FIRST_NAME + ", LAST_NAME=" + LAST_NAME
				+ ", EMAIL=" + EMAIL + ", PHONE_NUMBER=" + PHONE_NUMBER + ", HIRE_DATE=" + HIRE_DATE + ", JOB_ID="
				+ JOB_ID + ", SALARY=" + SALARY + ", COMMISSION_PCT=" + COMMISSION_PCT + ", MANAGER_ID=" + MANAGER_ID
				+ ", DEPARTMENT_ID=" + DEPARTMENT_ID + "]";
	}

}// end class
