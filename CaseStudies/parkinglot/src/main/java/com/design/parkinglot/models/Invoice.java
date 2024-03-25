package com.design.parkinglot.models;

import java.util.Date;

public class Invoice extends BaseModel{
    private Ticket ticket;
    private Date exitTime;
    private double amount;
    private Operator operator;
    private InvoicePaymentStatus invoicePaymentStatus;
}
