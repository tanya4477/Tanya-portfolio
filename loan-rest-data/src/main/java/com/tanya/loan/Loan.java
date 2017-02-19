package com.tanya.loan;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private double loanAmount;
	private int loanTermMonths;

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public int getLoanTermMonths() {
		return loanTermMonths;
	}

	public void setLoanTermMonths(int loanTermMonths) {
		this.loanTermMonths = loanTermMonths;
	}
}
