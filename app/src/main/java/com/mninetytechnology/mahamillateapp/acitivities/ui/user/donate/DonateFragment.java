package com.mninetytechnology.mahamillateapp.acitivities.ui.user.donate;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.mninetytechnology.mahamillateapp.acitivities.ui.user.MainActivity;
import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.custom.RadioGridGroup;
import com.mninetytechnology.mahamillateapp.databinding.FragmentDonateBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class DonateFragment extends Fragment {
    private FragmentDonateBinding binding;
    private MainActivity mActivity;
    private String amount;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDonateBinding.inflate(inflater, container, false);
        mActivity = (MainActivity) getActivity();
        binding.grpAmount.setOnCheckedChangeListener(new RadioGridGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGridGroup group, int checkedId) {
                binding.edtAmount.setText("");
                if (checkedId == R.id.rb_fifty) {
                    amount = "50.00";
                } else if (checkedId == R.id.rb_hundred) {
                    amount = "100.00";
                } else if (checkedId == R.id.rb_twofifty) {
                    amount = "250.00";
                } else if (checkedId == R.id.rb_fivehundred) {
                    amount = "500.00";
                } else if (checkedId == R.id.rb_thousand) {
                    amount = "1000.00";
                } else if (checkedId == R.id.rb_onethousandfivehundred) {
                    amount = "1500.00";
                }
            }
        });
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (amount != null) {
                    if (amount.trim().isEmpty()) {
                        if (binding.edtAmount.getText().toString().trim().isEmpty()) {
                            binding.edtAmount.setError(getString(R.string.empty_field));
                            binding.edtAmount.requestFocus();
                        }
                    } else {
                        if (!binding.edtAmount.getText().toString().trim().isEmpty()) {
                            amount = binding.edtAmount.getText().toString();
                        }
                        String paymentAddress = generateUrl("tifanfoundation@upi", "TIFAN FOUNDATION", amount);
                        Bitmap bitmap = generateQRCodeBitmap(paymentAddress);
                        AlertDialog dialog = new AlertDialog.Builder(mActivity).create();
                        ImageView imageView = new ImageView(mActivity);
                        imageView.setImageBitmap(bitmap);
                        dialog.setView(imageView);
                        dialog.setCancelable(false);
                        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Download QR Code", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialog.dismiss();
                                try {
                                    BitmapDrawable draw = (BitmapDrawable) imageView.getDrawable();
                                    Bitmap bitmap = draw.getBitmap();

                                    FileOutputStream outStream = null;
                                    File sdCard = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                                    File dir = new File(sdCard.getAbsolutePath() + "/MahaMillets");
                                    dir.mkdirs();
                                    String fileName = String.format("%d.jpg", System.currentTimeMillis());
                                    File outFile = new File(dir, fileName);
                                    outStream = new FileOutputStream(outFile);
                                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
                                    outStream.flush();
                                    outStream.close();

                                    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                                    intent.setData(Uri.fromFile(outFile));
                                    mActivity.sendBroadcast(intent);
                                    Toast.makeText(mActivity, mActivity.getString(R.string.downloaded_successfully), Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    Log.e("TAG", "onClick: "+e.getMessage());
                                }
                            }
                        });
                        dialog.show();
                    }
                } else {
                    mActivity.showErrorSnackBar(binding.getRoot(), getString(R.string.please_select_amount));
                }
            }
        });

        return binding.getRoot();
    }

    private Bitmap generateQRCodeBitmap(String paymentAddress) {
        QRCodeWriter writer = new QRCodeWriter();

        BitMatrix bitMatrix = null;
        try {

            bitMatrix = writer.encode(paymentAddress, BarcodeFormat.QR_CODE, 512, 512);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
            }
        }
        return bmp;
    }

    private String generateUrl(String upi, String merchant_name, String amount) {
        return "upi://pay?pa=" + upi + "&pn=" + merchant_name + "&am=" + amount + "&cu=INR&url=https://merchant.com";
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}