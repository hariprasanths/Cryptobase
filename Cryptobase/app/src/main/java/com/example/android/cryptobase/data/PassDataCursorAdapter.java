package com.example.android.cryptobase.data;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.cryptobase.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.ByteArrayOutputStream;

import static com.example.android.cryptobase.QR_code_generation.QRcodeWidth;

/**
 * Created by Hari on 11-07-2017.
 */

public class PassDataCursorAdapter extends CursorAdapter {

    Context mContext;
    public PassDataCursorAdapter(Context context, Cursor c) {
        super(context, c,false);
        mContext = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        return LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView usernameTextView = (TextView) view.findViewById(R.id.username_textview);
        TextView passwordTextView = (TextView) view.findViewById(R.id.password_textview);
        ImageView passwordQrCodeImageView = (ImageView) view.findViewById(R.id.qrcode_imageview);

        int usernameColumnIndex = cursor.getColumnIndex(PassDataContract.PassDataEntry.COLUMN_USERNAME);
        int passwordColumnIndex = cursor.getColumnIndex(PassDataContract.PassDataEntry.COLUMN_PASSWORD);
        //int passwordQrCodeIndex = cursor.getColumnIndex(PassDataContract.PassDataEntry.COLUMN_PASSWORD_QRCODE);

        String username = cursor.getString(usernameColumnIndex);
        String password = cursor.getString(passwordColumnIndex);
       /* byte[] passwordQrCodeByte = cursor.getBlob(passwordQrCodeIndex);
        passwordQrCodeImageView.setImageBitmap(getImage(passwordQrCodeByte));*/
        try
        {
            Bitmap passwordQrCodeBitmapImg = TextToImageEncode(password);
            passwordQrCodeImageView.setImageBitmap(passwordQrCodeBitmapImg);
        }catch ( WriterException e)
        {

        }
        usernameTextView.setText(username);
        passwordTextView.setText(password);
    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    private Bitmap TextToImageEncode(String text) throws WriterException
    {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    text,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    QRcodeWidth, QRcodeWidth, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {

            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ?
                        mContext.getResources().getColor(R.color.qrCodeBlack):mContext.getResources().getColor(R.color.qrCodeWhite);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }
}
