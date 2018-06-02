package com.example.android.notepad;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created by Yan on 2018/5/28.
 */

public class YanCursorAdapter extends SimpleCursorAdapter {
    public YanCursorAdapter(Context context, int layout, Cursor c,
                            String[] from, int[] to) {
        super(context, layout, c, from, to);
    }
    @Override
    public void bindView(View view, Context context, Cursor cursor){
        super.bindView(view, context, cursor);

        //从数据库中读取的cursor中获取笔记列表对应的颜色数据，并设置笔记颜色
        int x = cursor.getInt(cursor.getColumnIndex(NotePad.Notes.COLUMN_NAME_BACK_COLOR));
        /**
         * 银河白 255 255 255
         * 杏仁黄 250 249 222
         * 宁静蓝 137 171 227
         * 护眼绿 204 232 207
         * 蔷薇粉 242 121 222
         */
        switch (x){
            case NotePad.Notes.DEFAULT_COLOR:
                view.setBackgroundColor(Color.rgb(255, 255, 255));
                break;
            case NotePad.Notes.YELLOW_COLOR:
                view.setBackgroundColor(Color.rgb(250, 249, 222));
                break;
            case NotePad.Notes.BLUE_COLOR:
                view.setBackgroundColor(Color.rgb(137, 171, 227));
                break;
            case NotePad.Notes.GREEN_COLOR:
                view.setBackgroundColor(Color.rgb(204, 232, 207));
                break;
            case NotePad.Notes.RED_COLOR:
                view.setBackgroundColor(Color.rgb(242, 221, 222));
                break;
            default:
                view.setBackgroundColor(Color.rgb(255, 255, 255));
                break;
        }

    }
}
