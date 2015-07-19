package edu.roi.playbox.domain;

/**
 * ��� ����������� (�� ������ ������)
 * Created by AlexRP239 on 19.07.2015.
 */
public enum PaymentMethod {

    CREDIT_CARD("��������� �����", "credit_card", "creditCardPaymentService"),
    BANK_ACCOUNT("���������� �������", "bank_account", "bankAccountPaymentService");


    private final String displayName;
    private final String htmlTemplateName;
    private final String serviceBeanName;

    PaymentMethod(String displayName, String htmlTemplateName, String serviceBeanName) {
        this.displayName = displayName;
        this.htmlTemplateName = htmlTemplateName;
        this.serviceBeanName = serviceBeanName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getHtmlTemplateName() {
        return htmlTemplateName;
    }

    public String getServiceBeanName() {
        return serviceBeanName;
    }
}
