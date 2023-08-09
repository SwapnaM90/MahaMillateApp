package com.mninetytechnology.mahamillateapp.models.contracts;
/**
 * Created by Swapna Thakur on 3/1/2022.
 * Contract for otp
 */
public interface OtpContract {

    interface ViewModel {
//        void getOtp(OtpCheckResponseModel otpCheckResponseModel);
        void getOtp();

        void saveOtp(String otp);
        void showOtpFailed(String error);
    }
    interface Presenter {
        void sendOtp(String phone_number);

        void verifyOtp();
    }
}
