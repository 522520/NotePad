package com.example.android.notepad;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Yan on 2018/5/29.
 */

public class NoteFontSize extends Activity {

    private Cursor mCursor;
    private Uri mUri;
    private int size;
    private static final int COLUMN_INDEX_TITLE = 1;


    private static final String[] PROJECTION = new String[]{
            NotePad.Notes._ID, // 0
            NotePad.Notes.COLUMN_NAME_FONT_SIZE
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_font_size);
        mUri = getIntent().getData();
        mCursor = managedQuery(
                mUri,        // The URI for the note that is to be retrieved.
                PROJECTION,  // The columns to retrieve
                null,        // No selection criteria are used, so no where columns are needed.
                null,        // No where columns are used, so no where values are needed.
                null         // No sort order is needed.
        );

    }

    @Override
    protected void onResume() {
        if (mCursor != null) {
            mCursor.moveToFirst();
            size = mCursor.getInt(COLUMN_INDEX_TITLE);
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ContentValues values = new ContentValues();
        values.put(NotePad.Notes.COLUMN_NAME_FONT_SIZE, size);
        getContentResolver().update(mUri, values, null, null);

    }

    public void small(View view) {
        size = NotePad.Notes.FONT_20;

    }

    public void middle(View view) {
        size = NotePad.Notes.FONT_30;
        finish();
    }

    public void big(View view) {
        size = NotePad.Notes.FONT_40;
        finish();
    }


}
