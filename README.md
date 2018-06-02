# NotePad
This is an AndroidStudio rebuild of google SDK sample NotePad
# Welcome to My Notepad!

	简介
		基于NotePad应用做功能扩展,以下是对Notepad拓展功能的说明。
 

 - 基本要求：

	* NoteList中显示条目增加时间戳显示

	* 添加笔记查询功能（根据标题查询）

 - 附加功能：

	* UI美化
		* 图标美化
		* 搜索框美化
		* 时间戳格式优化
	* 更改记事本的背景
	* 语音笔记
	* 文字样式
	* 笔记排序
## 整体界面概览

 - 主界面

	 - ![image](https://github.com/522520/NotePad/blob/master/images/yan.1.png)
	 - ![image](https://github.com/522520/NotePad/blob/master/images/yan.3.png)
	 - ![image](https://github.com/522520/NotePad/blob/master/images/yan.4.png)

- 编辑笔记
	- ![image](https://github.com/522520/NotePad/blob/master/images/yan.9.png)

 - 改变背景颜色
	- ![image](https://github.com/522520/NotePad/blob/master/images/yan.2.png)

 - 改变字体大小
	- ![image](https://github.com/522520/NotePad/blob/master/images/yan.8.png)

	- ![image](https://github.com/522520/NotePad/blob/master/images/yan.10.png)

 - 搜索

	- ![image](https://github.com/522520/NotePad/blob/master/images/yan.5.png)

	- ![image](https://github.com/522520/NotePad/blob/master/images/yan.6.png)

 - 排序
 
	- ![image](https://github.com/522520/NotePad/blob/master/images/yan.7.png)

 - 语音笔记

	- ![image](https://github.com/522520/NotePad/blob/master/images/yan.11.png)

	- ![image](https://github.com/522520/NotePad/blob/master/images/yan.12.png)

	- ![image](https://github.com/522520/NotePad/blob/master/images/yan.13.png)

## 主要代码说明
> **时间戳显示**
> 对于不同年月日的不同显示格式，以及当天分为早，中，晚，凌晨，下午格式（仿微信聊天）。

    static String dayNames[] = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};  
    public static String LongFormatTime(long timesamp) {  
	    String result = "";  
	    Calendar todayCalendar = Calendar.getInstance();  
	    Calendar otherCalendar = Calendar.getInstance();  
	    otherCalendar.setTimeInMillis(timesamp);  
  
	    String timeFormat = "M月d日 HH:mm";  
	    String yearTimeFormat = "yyyy年M月d日 HH:mm";  
	    String am_pm = "";  
	    int hour = otherCalendar.get(Calendar.HOUR_OF_DAY);  
	    if (hour >= 0 && hour < 6) {  
	        am_pm = "凌晨";  
	    } else if (hour >= 6 && hour < 12) {  
	        am_pm = "早上";  
	    } else if (hour == 12) {  
        am_pm = "中午";  
	    } else if (hour > 12 && hour < 18) {  
	        am_pm = "下午";  
	    } else if (hour >= 18) {  
	        am_pm = "晚上";  
	    }  
    timeFormat = "M月d日 " + am_pm + "HH:mm";  
    yearTimeFormat = "yyyy年M月d日 " + am_pm + "HH:mm";  
  
    boolean yearTemp = todayCalendar.get(Calendar.YEAR) == otherCalendar.get(Calendar.YEAR);  
    if (yearTemp) {  
        int todayMonth = todayCalendar.get(Calendar.MONTH);  
        int otherMonth = otherCalendar.get(Calendar.MONTH);  
        if (todayMonth == otherMonth) {//表示是同一个月  
        int temp = todayCalendar.get(Calendar.DATE) - otherCalendar.get(Calendar.DATE);  
            switch (temp) {  
                case 0:  
                    result = am_pm+getHourAndMin(timesamp);  
                    break;  
                case 1:  
                    result = "昨天 " +am_pm+ getHourAndMin(timesamp);  
                    break;  
                case 2:  
                case 3:  
                case 4:  
                case 5:  
                case 6:  
                    int dayOfMonth = otherCalendar.get(Calendar.WEEK_OF_MONTH);  
                    int todayOfMonth = todayCalendar.get(Calendar.WEEK_OF_MONTH);  
                    if (dayOfMonth == todayOfMonth) {//表示是同一周
                    int dayOfWeek = otherCalendar.get(Calendar.DAY_OF_WEEK);  
                        if (dayOfWeek != 1) {//判断当前是不是星期日     如想显示为：周日 12:09 可去掉此判断  
                         result = dayNames[otherCalendar.get(Calendar.DAY_OF_WEEK) - 1]+am_pm + getHourAndMin(timesamp);  
                        } else {  
                            result = getTime(timesamp, timeFormat);  
                        }  
                    } else {  
                        result = getTime(timesamp, timeFormat);  
                    }  
                    break;  
                default:  
                    result = getTime(timesamp, timeFormat);  
                    break;  
            }  
        } else {  
            result = getTime(timesamp, timeFormat);  
        }  
    } else {  
        result = getYearTime(timesamp, yearTimeFormat);  
    }  
    return result;  
    }  
    /**  
    * 当天的显示时间格式  
    *   
    *  @param time  
    *   @return  
    *  */  
    public static String getHourAndMin(long time) {  
    SimpleDateFormat format = new SimpleDateFormat("HH:mm");  
    return format.format(new Date(time));  
    }  
    /** 
    * 不同一周的显示时间格式  
    *    
    *  @param time  
    *  @param timeFormat  
    *  @return  
    * /  
    * public static String getTime(long time, String timeFormat) {  
    SimpleDateFormat format = new SimpleDateFormat(timeFormat);  
    return format.format(new Date(time));  
    }  
    /**  
     * 不同年的显示时间格式  
     *  
     * @param time  
     * @param yearTimeFormat  
     * @return  
     * / 
     public static String getYearTime(long time, String yearTimeFormat) {  
    SimpleDateFormat format = new SimpleDateFormat(yearTimeFormat);  
    return format.format(new Date(time));  
    }

> **笔记查询**
> 在case语句中写跳转activity代码之前要先写好搜索的activity，新建一个名为NoteSearch的activity。由于搜索出来的也是笔记列表，所以可以模仿NoteList的activity继承ListActivity。在安卓中有个用于搜索控件：SearchView，可以把SearchView跟ListView相结合，动态地显示搜索结果，来布局搜索页面，再来优化搜索框，去掉下划线，并加入搜索为空时提示框。

    public class NoteSearch extends ListActivity implements SearchView.OnQueryTextListener {  
    private static final String[] PROJECTION = new String[]{  
            NotePad.Notes._ID, // 0 
            NotePad.Notes.COLUMN_NAME_TITLE, // 1  
             //扩展 显示时间 颜色
             NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE, // 2  
			 NotePad.Notes.COLUMN_NAME_BACK_COLOR,  
             NotePad.Notes.COLUMN_NAME_FONT_SIZE  
	  };  
	  @Override  
	  protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.note_search_list);  
        Intent intent = getIntent();  
        if (intent.getData() == null) {  
            intent.setData(NotePad.Notes.CONTENT_URI);  
        }  
        SearchView searchview = (SearchView) findViewById(R.id.search_view);  
	  //去掉下划线
        if (searchview != null) {  
            try {        //--拿到字节码  
	  Class<?> argClass = searchview.getClass();  
                //--指定某个私有属性,mSearchPlate是搜索框父布局的名字  
	  Field ownField = argClass.getDeclaredField("mSearchPlate");  
                //--暴力反射,只有暴力反射才能拿到私有属性  
	  ownField.setAccessible(true);  
                View mView = (View) ownField.get(searchview);  
                //--设置背景  
	  mView.setBackgroundColor(Color.TRANSPARENT);  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
  
	       //为查询文本框注册监听器  
	  searchview.setOnQueryTextListener(NoteSearch.this);  
    }  
  
    @Override  
	  public boolean onQueryTextSubmit(String query) {  
            return false;  
    }  
  
    @Override  
	  public boolean onQueryTextChange(String newText) {  
        String selection = NotePad.Notes.COLUMN_NAME_TITLE + " Like ? ";  
        String[] selectionArgs = {"%" + newText + "%"};  
        Cursor cursor = managedQuery(  
                getIntent().getData(),            // Use the default content URI for the provider.  
	  PROJECTION,                       // Return the note ID and title for each 	note. and modifcation date  
	  selection,                        // 条件左边  
	  selectionArgs,                    // 条件右边  
	  NotePad.Notes.DEFAULT_SORT_ORDER // Use the default sort order.  
	  );  
        if(cursor.getCount()==0){  
            Toast.makeText(this,"sorry,no such note",Toast.LENGTH_SHORT).show();  
  
        }  
        String[] dataColumns = {NotePad.Notes.COLUMN_NAME_TITLE, NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE};  
        int[] viewIDs = {android.R.id.text1, R.id.text1_time};  
        YanCursorAdapter adapter = new YanCursorAdapter(  
                this,  
                R.layout.noteslist_item,  
                cursor,  
                dataColumns,  
                viewIDs  
        );  
        setListAdapter(adapter);  
        return true;  
    }  
  
    @Override  
	  protected void onListItemClick(ListView l, View v, int position, long id) {  
        // Constructs a new URI from the incoming URI and the row ID  
	  Uri uri = ContentUris.withAppendedId(getIntent().getData(), id);  
        // Gets the action from the incoming Intent  
	  String action = getIntent().getAction();  
        // Handles requests for note data  
	  if (Intent.ACTION_PICK.equals(action) || Intent.ACTION_GET_CONTENT.equals(action)) {  
            // Sets the result to return to the component that called this Activity. The  
	 // result contains the new URI  setResult(RESULT_OK, new Intent().setData(uri));  
        } else {  
            // Sends out an Intent to start an Activity that can handle ACTION_EDIT. The  
	 // Intent's data is the note ID URI. The effect is to call NoteEdit.  startActivity(new Intent(Intent.ACTION_EDIT, uri));  
        }  
    }  
	}
	

> **改变背景颜色**
数据库表加入color字段，以整型定义在契约类，NotePadProvider.class中PROJECTION，sNotesProjectionMap，values作相应补充，自定义simplecursoradapter,再对color进行布局，对应activity，最后再NoteEditor.class的onResume()方法读取颜色数据，并设置编辑背景。

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
	 */  switch (x){  
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
NoteColor.class

    public class NoteColor extends Activity {  
  
    private Cursor mCursor;  
    private Uri mUri;  
    private int color;  
    private static final int COLUMN_INDEX_TITLE = 1;  
  
    private static final String[] PROJECTION = new String[]{  
            NotePad.Notes._ID, // 0  
	  NotePad.Notes.COLUMN_NAME_BACK_COLOR  
	  };  
  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.note_color);  
        mUri = getIntent().getData();  
        mCursor = managedQuery(  
                mUri,        // The URI for the note that is to be retrieved.  
	  PROJECTION,  // The columns to retrieve  
	  null,        // No selection criteria are used, so no where columns are needed.  
	  null,        // No where columns are used, so no where values are needed.  
	  null // No sort order is needed.  
	  );  
  
    }  
  
    @Override  
	  protected void onResume() {  
        if (mCursor != null) {  
            mCursor.moveToFirst();  
            color = mCursor.getInt(COLUMN_INDEX_TITLE);  
        }  
        super.onResume();  
    }  
  
    @Override  
	  protected void onPause() {  
        super.onPause();  
        ContentValues values = new ContentValues();  
        values.put(NotePad.Notes.COLUMN_NAME_BACK_COLOR, color);  
        getContentResolver().update(mUri, values, null, null);  
  
    }  
  
    public void white(View view) {  
        color = NotePad.Notes.DEFAULT_COLOR;  
        finish();  
    }  
  
    public void yellow(View view) {  
        color = NotePad.Notes.YELLOW_COLOR;  
        finish();  
    }  
  
    public void blue(View view) {  
        color = NotePad.Notes.BLUE_COLOR;  
        finish();  
    }  
  
    public void green(View view) {  
        color = NotePad.Notes.GREEN_COLOR;  
        finish();  
    }  
  
    public void red(View view) {  
        color = NotePad.Notes.RED_COLOR;  
        finish();  
    }  
	}

> **改变字体大小**
> 实现原理与color相似，给出布局文件。

    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"  
	  android:orientation="horizontal"  
	  android:layout_width="match_parent"  
	  android:layout_height="match_parent"  
	  android:background="@color/colorWhite"  
	  android:paddingLeft="50dp">  
  
	    <ImageButton  
	  android:id="@+id/small"  
	  android:layout_width="50dp"  
	  android:layout_height="50dp"  
	  android:background="@drawable/small"  
	  android:onClick="small"  
	  android:layout_marginRight="20dp"/>  
	    <ImageButton  
	  android:id="@+id/middle"  
	  android:layout_width="50dp"  
	  android:layout_height="50dp"  
	  android:background="@drawable/middle"  
	  android:onClick="middle"  
	  android:layout_marginRight="20dp"/>  
	    <ImageButton  
	  android:id="@+id/big"  
	  android:layout_width="50dp"  
	  android:layout_height="50dp"  
	  android:background="@drawable/big"  
	  android:onClick="big"/>  
  
	</LinearLayout>
	

> **语音笔记**
> 采用的是百度语音SDK，导入语音识别SDK，eg:[Baidu-Voice-SDK-Android-1.6.2.zip](http://bos.nj.bpc.baidu.com/v1/audio/Baidu-Voice-SDK-Android-1.6.2.zip),将libs和res文件夹拷到相应目录,将libs中的.jar和.so添加为库文件,修改build.gradle配置文件, AndroidManifest.xml设置权限,NoteEdit.class对应EditView。

build.gradle

    apply plugin: 'com.android.application'  
	android {  
    compileSdkVersion 23  
	  buildToolsVersion "23.0.1"  
	  defaultConfig {  
        applicationId "com.example.android.notepad"  
	  minSdkVersion 11  
	  targetSdkVersion 11  
	  versionCode 1  
	  versionName "1.0"  
	  testApplicationId "com.example.android.notepad.tests"  
	  testInstrumentationRunner "android.test.InstrumentationTestRunner"  
	  }  
  
        buildTypes {  
            release {  
                minifyEnabled false  
	  proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.txt'  
	  }  
        }  
        task nativeLibsToJar(type: Zip, description: "create a jar archive of the native libs") {  
            destinationDir file("$projectDir/libs")  
            baseName "Native_Libs2"  
	  extension "jar"  
	  from fileTree(dir: "libs", include: "armeabi/.so") into "lib"  
	  }  
        tasks.withType(JavaCompile) {  
            compileTask -> compileTask.dependsOn(nativeLibsToJar)  
        }  
        sourceSets {  
            main {  
                jniLibs.srcDirs = ['libs']  
            }  
        }  
    }  
  
  
	dependencies {  
    compile fileTree(exclude: '*.bak', dir: 'libs')  
    compile files('libs/Baidu-SpeechRecognitionUI-SDK-Android-1.6.2.jar')  
    compile files('libs/VoiceRecognition-1.6.2.jar')  
	}
NoteEditor.class

    private BaiduASRDigitalDialog mDialog=null;  
	private DialogRecognitionListener mDialogListener=null;  
	//自行申请
	private String API_KEY="CtYCSuoPgLm4sZnHjdylpD2e";  
	private String SECRET_KEY="06cfd69c49cfd5ddd0717f187999081b";
	private final void voice(){  
    mText=(EditText)findViewById(R.id.note);  
  
    if (mDialog == null) {  
        if (mDialog != null) {  
            mDialog.dismiss();  
        }  
        Bundle params = new Bundle();  
        //设置API_KEY, SECRET_KEY  
	  params.putString(BaiduASRDigitalDialog.PARAM_API_KEY, API_KEY);  
        params.putString(BaiduASRDigitalDialog.PARAM_SECRET_KEY, SECRET_KEY);  
        //设置语音识别对话框为蓝色高亮主题  
	  params.putInt(BaiduASRDigitalDialog.PARAM_DIALOG_THEME, BaiduASRDigitalDialog.THEME_BLUE_LIGHTBG);  
        //实例化百度语音识别对话框  
	  mDialog = new BaiduASRDigitalDialog(this, params);  
        //设置百度语音识别回调接口  
	  mDialogListener=new DialogRecognitionListener()  
        {  
  
            @Override  
	  public void onResults(Bundle mResults)  
            {  
                ArrayList<String> rs = mResults != null ? mResults.getStringArrayList(RESULTS_RECOGNITION) : null;  
                if (rs != null && rs.size() > 0) {  
                    mText.setText(rs.get(0));  
                }  
  
            }  
  
        };  
        mDialog.setDialogRecognitionListener(mDialogListener);  
    }  
  
  
    mDialog.show();  
  
	}

> **笔记排序**
> 主要对cursor的query中sort进行操作，可通过标题，修改时间，颜色等升降排序，运用AlertDialog显示。

    /**  
	 * customized method which can sort the sequence of items in the listview */public void sortListItem() {  
    AlertDialog.Builder builder = new AlertDialog.Builder(this);  
    builder.setTitle("Sort Way");  
    final String[] sortOrders = new String[]{"By title ", "By modifiedTime",  
            "By modifiedTime desc", "By color"};  
  
    builder.setSingleChoiceItems(sortOrders, -1, new DialogInterface.OnClickListener() {  
  
        String order = NotePad.Notes.DEFAULT_SORT_ORDER;  
  
        @Override  
	  public void onClick(DialogInterface dialog, int which) {  
            String sort_mode = sortOrders[which];  
            switch (sort_mode) {  
                case "By title":  
                    //sort the items of listview by title ascending  
	  order = NotePad.Notes.COLUMN_NAME_TITLE;  
                    break;  
                case "By modifiedTime":  
                    //sort the items of listview by  modified date ascending  
	  order = NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE;  
                    break;  
                case "By modifiedTime desc":  
                    //按修改时间降序排列  
	  //sort the items of listview by modified date descending  
	  order = NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE_DESC;  
                    break;  
                case "By color":  
                    //sort the items of listview by color  
	  order = NotePad.Notes.COLUMN_NAME_BACK_COLOR;  
                    break;  
                default:  
                    break;  
            }  
  
            cursor = managedQuery(getIntent().getData(), PROJECTION, null, null, order);  
  
            adapter = new YanCursorAdapter(  
                    NotesList.this,  
                    R.layout.noteslist_item,  
                    cursor,  
                    dataColumns,  
                    viewIDs  
	  );  
  
            setListAdapter(adapter);  
            //refresh the data of listview  
	  adapter.notifyDataSetChanged();  
            //when the user selected an option,the dialog dismissed.  
	  dialog.dismiss();  
        }  
    });  
    //show the dialog  
	  builder.show();  
	}
## 总结
此次NotePad实验，在实现拓展功能出现诸多bug，后来一步步解决的过程中，发现了很多细节问题，比如数据库的空格，id的不同定义用法等，总的一句，实践中才会学的更多。
### 资料链接
[NotePad原应用源码](https://github.com/llfjfz/NotePad)
