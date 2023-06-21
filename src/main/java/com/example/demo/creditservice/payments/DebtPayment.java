package com.example.demo.creditservice.payments;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Scanner;

public class DebtPaymentCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the monthly payment amount: ");
        double monthlyPayment = scanner.nextDouble();

        System.out.print("Enter the interest rate (monthly): ");
        double interestRate = scanner.nextDouble();

        System.out.print("Enter the total debt amount: ");
        double totalDebt = scanner.nextDouble();

        int monthsToPayOff = calculateMonthsToPayOff(monthlyPayment, interestRate, totalDebt);

        LocalDate currentDate = LocalDate.now();
        LocalDate payOffDate = currentDate.plusMonths(monthsToPayOff);
        payOffDate = payOffDate.with(TemporalAdjusters.lastDayOfMonth());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        String formattedPayOffDate = payOffDate.format(formatter);

        System.out.println("The debt will be paid off in " + formattedPayOffDate);
    }

    public static int calculateMonthsToPayOff(double monthlyPayment, double interestRate, double totalDebt) {
        double remainingDebt = totalDebt;
        int months = 0;

        while (remainingDebt > 0) {
            double interestAmount = remainingDebt * interestRate;
            remainingDebt += interestAmount - monthlyPayment;
            months++;
        }

        return months;
    }
}
