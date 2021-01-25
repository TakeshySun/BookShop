package desktop.pages;

import desktop.fragments.checkOutComponents.CheckOutDisablePaymentComponent;
import desktop.fragments.checkOutComponents.CheckOutPaymentComponent;
import desktop.fragments.checkOutComponents.CheckOutPersonalInformationComponent;
import desktop.fragments.checkOutComponents.CheckOutTotalComponent;

public class CheckOutPageImpl {
    public CheckOutPersonalInformationComponent checkOutPersonalInformationComponent = new CheckOutPersonalInformationComponent();
    public CheckOutTotalComponent checkOutTotalComponent = new CheckOutTotalComponent();
    public CheckOutPaymentComponent checkOutPaymentComponent = new CheckOutPaymentComponent();
    public CheckOutDisablePaymentComponent checkOutDisablePaymentComponent = new CheckOutDisablePaymentComponent();
}
