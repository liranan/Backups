package com.cn.edu.uestc.graduationProject.backups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

@SuppressLint("SdCardPath")
public class MainActivity extends ActionBarActivity implements
		fragmentMain.twoListener, fragmentOne.oneListener,
		fragmentThree.threeListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	private ViewPager mViewPager;
	private List<Fragment> fragmentList;
	private fragmentOne Fone;
	private fragmentMain Fmain;
	private fragmentThree Fthree;

	protected ProgressDialog pd1;
	protected ProgressDialog pd2;
	protected ProgressDialog pd3;
	protected ProgressDialog pd4;
	protected ProgressDialog pd5;
	protected ProgressDialog pd6;

	// private ImageButton btn1, btn3, saveBtn, exBtn;
	// private Button btn2, btn4;
	// private CheckBox Brecord, Bphone, Bsms, Rrecord, Rphone, Rsms;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		fragmentList = new ArrayList<Fragment>();
		Fone = new fragmentOne();
		Fmain = new fragmentMain();
		Fthree = new fragmentThree();

		fragmentList.add(Fone);
		fragmentList.add(Fmain);
		fragmentList.add(Fthree);

		// btn1 = (ImageButton) findViewById(R.id.btn1);
		// btn1.setOnClickListener(this);
		// btn3 = (ImageButton) findViewById(R.id.btn3);
		// btn3.setOnClickListener(this);
		// saveBtn = (ImageButton) findViewById(R.id.saveBtn);
		// saveBtn.setOnClickListener(this);
		// exBtn = (ImageButton) findViewById(R.id.exBtn);
		// exBtn.setOnClickListener(this);
		//
		// btn2 = (Button) findViewById(R.id.btn2);
		// btn2.setOnClickListener(this);
		// btn4 = (Button) findViewById(R.id.btn4);
		// btn4.setOnClickListener(this);
		//
		// Brecord = (CheckBox) findViewById(R.id.Brecord);
		// Bphone = (CheckBox) findViewById(R.id.Bphone);
		// Bsms = (CheckBox) findViewById(R.id.Bsms);
		// Rrecord = (CheckBox) findViewById(R.id.Rrecord);
		// Rphone = (CheckBox) findViewById(R.id.Rphone);
		// Rsms = (CheckBox) findViewById(R.id.Rsms);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager.setCurrentItem(1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class
			// below).
			return fragmentList.get(position);
			// return PlaceholderFragment.newInstance(position + 1);
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return fragmentList.size();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

	// public Fragment createFragment() {
	// return new fragmentMain();
	// }

	@Override
	
	public void onOneListener(int jud) {
		// TODO Auto-generated method stub
		if (jud == 0) {
			pd1 = ProgressDialog.show(this, "请稍候..", "正在对通讯记录进行安全备份...", true,
					false);
			Thread t1 = new Thread(sCallsRunnable);
			t1.start();
		} else if (jud == 1) {
			pd2 = ProgressDialog.show(this, "请稍候..", "正在对联系人进行安全备份...", true,
					false);
			Thread t2 = new Thread(sPhoneRunnable);
			t2.start();
		} else if (jud == 2) {
			pd3 = ProgressDialog.show(this, "请稍候..", "正在对短信进行安全备份...", true,
					false);
			Thread t3 = new Thread(sSmsRunnable);
			t3.start();
		}
	}

	@Override
	public void onTwoListener(int a) {
		// TODO Auto-generated method stub
		mViewPager.setCurrentItem(a);
	}

	@Override
	public void onThreeListener(int jud) {
		// TODO Auto-generated method stub
		if (jud == 0) {
			pd4 = ProgressDialog.show(this, "请稍候..", "正在恢复通讯记录信息...", true,
					false);
			Thread t4 = new Thread(eCallsRunnable);
			t4.start();
		} else if (jud == 1) {
			pd5 = ProgressDialog.show(this, "请稍候..", "正在恢复联系人信息...", true,
					false);
			Thread t5 = new Thread(ePhoneRunnable);
			t5.start();
		} else if (jud == 2) {
			pd6 = ProgressDialog
					.show(this, "请稍候..", "正在恢复短信信息...", true, false);
			Thread t6 = new Thread(eSmsRunnable);
			t6.start();
		}
	}

	/*
	 * Runnables
	 */
	Runnable sCallsRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				saveCalls();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pd1.dismiss();
		}
	};
	Runnable sPhoneRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				savePhone();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pd2.dismiss();
		}
	};
	Runnable sSmsRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				saveSms();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pd3.dismiss();
		}
	};
	Runnable eCallsRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				exCalls();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pd4.dismiss();
		}
	};
	Runnable ePhoneRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				exPhone();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pd5.dismiss();
		}
	};
	Runnable eSmsRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			exSms();
			pd6.dismiss();
		}
	};

	protected void saveCalls() throws IOException {
		// TODO Auto-generated method stub
		OutputStreamWriter outWriter;
		try {
			File callsFile = new File("mnt/sdcard", "backups.calls");
			FileOutputStream os = new FileOutputStream(callsFile);
			outWriter = new OutputStreamWriter(os, "UTF-8");
		} catch (Exception e) {
			return;
		}
		Cursor cursor = getContentResolver().query(CallLog.Calls.CONTENT_URI,
				null, null, null, null);
		int idxNumber = cursor.getColumnIndex(CallLog.Calls.NUMBER);
		int idxDate = cursor.getColumnIndex(CallLog.Calls.DATE);
		int idxType = cursor.getColumnIndex(CallLog.Calls.TYPE);
		int idxDur = cursor.getColumnIndex(CallLog.Calls.DURATION);
		// for (cursor.moveToFirst(); !cursor.isAfterLast();
		// cursor.moveToNext()) {
		while (cursor.moveToNext()) {
			outWriter.write("CALL:" + cursor.getString(idxNumber) + ","
					+ cursor.getLong(idxDate) + "," + cursor.getString(idxType)
					+ "," + cursor.getInt(idxDur) + "\r\n");
			outWriter.flush();
		}

		outWriter.close();
		cursor.close();
	}

	protected void saveSms() throws IOException {
		// TODO Auto-generated method stub
		OutputStreamWriter outWriter;
		try {
			File smsFile = new File("mnt/sdcard", "backups.sms");
			FileOutputStream os = new FileOutputStream(smsFile);
			outWriter = new OutputStreamWriter(os, "UTF-8");
		} catch (Exception e) {
			return;
		}
		Cursor cursor = getContentResolver().query(Uri.parse("content://sms/"),
				null, null, null, null);
		int idxNumber = cursor.getColumnIndex("address");
		int idxDate = cursor.getColumnIndex("date");
		int idxType = cursor.getColumnIndex("type");
		int idxBody = cursor.getColumnIndex("body");
		// for (cursor.moveToFirst(); !cursor.isAfterLast();
		// cursor.moveToNext()) {
		while (cursor.moveToNext()) {
			if (cursor.getString(idxBody) != null)
				outWriter.write("SMS:" + cursor.getString(idxNumber) + ","
						+ cursor.getLong(idxDate) + ","
						+ cursor.getString(idxType) + ","
						+ cursor.getString(idxBody) + "\r\n");
		}
		outWriter.close();
		cursor.close();
	}

	// protected void savePhone() throws IOException {
	// // TODO Auto-generated method stub
	// OutputStreamWriter outWriter;
	// int i = 0;
	// try {
	// File smsFile = new File("mnt/sdcard", "backups.vcf");
	// FileOutputStream os = new FileOutputStream(smsFile);
	// outWriter = new OutputStreamWriter(os, "UTF-8");
	// } catch (Exception e) {
	// return;
	// }
	// Cursor cursor = getContentResolver().query(
	// ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
	// // while (cursor.moveToNext()) {
	// for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
	// Log.e("jj",
	// Integer.toString(cursor.getColumnIndex(Phone.NUMBER))
	// + Integer.toString(cursor
	// .getColumnIndex(Phone.DISPLAY_NAME)));
	// i++;
	// Log.e("jj",
	// "contact:"
	// + Integer.toString(i)
	// + cursor.getString(cursor
	// .getColumnIndex(Phone.NUMBER))
	// + ","
	// + cursor.getString(cursor
	// .getColumnIndex(Phone.DISPLAY_NAME)));
	// }
	// // outWriter.write("SMS:" + cursor.getString(idxNumber) + ","
	// // + cursor.getLong(idxDate) + ","
	// // + cursor.getString(idxType) + ","
	// // + cursor.getString(idxBody) + "\r\n");
	// outWriter.close();
	// cursor.close();
	// }

	public void savePhone() throws IOException {
		OutputStreamWriter outWriter;
		try {
			File csvFile = new File("mnt/sdcard", "backups.vcf");
			FileOutputStream os = new FileOutputStream(csvFile);
			outWriter = new OutputStreamWriter(os, "UTF-8");
		} catch (Exception e) {
			return;
		}

		Cursor cursor = getContentResolver().query(
				ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		int idxIDofContacts = cursor
				.getColumnIndex(ContactsContract.Contacts._ID);
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			outWriter.write("BEGIN:VCARD\r\n");
			outWriter.write("VERSION:3.0\r\n");

			int contactid = cursor.getInt(idxIDofContacts);
			Cursor rawcur = getContentResolver()
					.query(ContactsContract.RawContacts.CONTENT_URI,
							null,
							ContactsContract.RawContacts.CONTACT_ID + " = "
									+ contactid, null, null);
			if (rawcur.moveToFirst()) {
				int rawid = cursor.getInt(cursor
						.getColumnIndex(ContactsContract.RawContacts._ID));
				Cursor datas = getContentResolver().query(
						ContactsContract.Data.CONTENT_URI, null,
						ContactsContract.Data.RAW_CONTACT_ID + " = " + rawid,
						null, null);
				for (datas.moveToFirst(); !datas.isAfterLast(); datas
						.moveToNext()) {
					storeContact(datas, outWriter);
				}
				datas.close();
			}
			rawcur.close();
			outWriter.write("END:VCARD\r\n");
			outWriter.flush();

		}
		outWriter.close();
		cursor.close();
		// getContentResolver().delete(
		// Uri.parse(ContactsContract.RawContacts.CONTENT_URI.toString()
		// + "?" + ContactsContract.CALLER_IS_SYNCADAPTER
		// + "=true"), ContactsContract.RawContacts._ID, null);
	}

	public void storeContact(Cursor cur, OutputStreamWriter outWriter)
			throws IOException {
		String t = cur.getString(cur
				.getColumnIndex(ContactsContract.Data.MIMETYPE));
		if (ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE.equals(t)) {
			String phone = cur
					.getString(cur
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			int type = cur
					.getInt(cur
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
			switch (type) {
			case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
				outWriter.write("TEL;TYPE=HOME:" + phone + "\r\n");
				break;
			case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
				outWriter.write("TEL;TYPE=CELL:" + phone + "\r\n");
				break;
			case ContactsContract.CommonDataKinds.Phone.TYPE_MAIN:
				outWriter.write("TEL;TYPE=PREF:" + phone + "\r\n");
				break;
			default:
				outWriter.write("TEL;TYPE=WORK:" + phone + "\r\n");
				break;
			}
		} else if (ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE
				.equals(t)) {
			String email = cur
					.getString(cur
							.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
			int type = cur
					.getInt(cur
							.getColumnIndex(ContactsContract.CommonDataKinds.Email.TYPE));
			switch (type) {
			case ContactsContract.CommonDataKinds.Email.TYPE_HOME:
				outWriter.write("EMAIL;TYPE=HOME:" + email + "\r\n");
				break;
			case ContactsContract.CommonDataKinds.Email.TYPE_WORK:
				outWriter.write("EMAIL;TYPE=PREF:" + email + "\r\n");
				break;
			default:
				outWriter.write("EMAIL;TYPE=OTHER" + email + "\r\n");
				break;
			}
		} else if (ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE
				.equals(t)) {
			String im = cur.getString(cur
					.getColumnIndex(ContactsContract.CommonDataKinds.Im.DATA));
			int protocol = cur
					.getInt(cur
							.getColumnIndex(ContactsContract.CommonDataKinds.Im.PROTOCOL));
			switch (protocol) {
			case ContactsContract.CommonDataKinds.Im.PROTOCOL_GOOGLE_TALK:
				outWriter.write("X-GOOGLE_TALK" + im + "\r\n");
				break;
			case ContactsContract.CommonDataKinds.Im.PROTOCOL_QQ:
				outWriter.write("X-QQ" + im + "\r\n");
				break;
			default:
				outWriter.write("X-OTHER" + im + "\r\n");
				break;
			}
		} else if (ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE
				.equals(t)) {
			String dname = cur
					.getString(cur
							.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME));
			String fname = cur
					.getString(cur
							.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME));
			String gname = cur
					.getString(cur
							.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME));
			if (fname != null)
				outWriter.write("N:" + fname + ";");
			else
				outWriter.write("N:;");
			if (gname != null)
				outWriter.write(gname);
			outWriter.write(";;;\r\n");
			outWriter.write("FN:" + dname + "\r\n");
		}
	}

	protected void exSms() {
		// TODO Auto-generated method stub
		String s = null;
		final String ADDRESS = "address";
		final String DATE = "date";
		final String TYPE = "type";
		final String BODY = "body";
		ContentValues values = new ContentValues();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream("/sdcard/backups.sms")));
			try {
				while ((s = br.readLine()) != null) {
					if (s.startsWith("SMS:")) {
						int index = s.indexOf(":");
						String content = s.substring(index + 1, s.length());
						String[] cateArr = content.split(",");
						for (int j = 0; j < cateArr.length; j++) {
							if (j == 0) {
								String address = cateArr[j];
								values.put(ADDRESS, address);
							}
							if (j == 1) {
								String data = cateArr[j];
								values.put(DATE, data);
							}
							if (j == 2) {
								String type = cateArr[j];
								values.put(TYPE, type);
							}
							if (j == 3) {
								String body = cateArr[j];
								values.put(BODY, body);
							}
						}
					}
					// if (temp != null) {
					getContentResolver().insert(Uri.parse("content://sms"),
							values);
					values.clear();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// protected void exPhone() throws IOException {
	// // TODO Auto-generated method stub
	//
	// }

	public void exPhone() throws FileNotFoundException {
		String s = null;
		String l = "END:VCARD";
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream("mnt/sdcard/backups.vcf")));
		try {
			while ((s = br.readLine()) != null) {
				ContentValues values = new ContentValues();
				Uri rawContactUri = getContentResolver().insert(
						RawContacts.CONTENT_URI, values);
				long rawContactsId = ContentUris.parseId(rawContactUri);
				while ((s = br.readLine()).equals(l) == false) {
					if (s.startsWith("FN:")) {
						String FN = s.substring(3);
						values.clear();
						values.put(StructuredName.RAW_CONTACT_ID, rawContactsId);
						values.put(Data.MIMETYPE,
								StructuredName.CONTENT_ITEM_TYPE);
						values.put(StructuredName.DISPLAY_NAME, FN);
						getContentResolver().insert(Data.CONTENT_URI, values);
					} else if (s.startsWith("TEL;TYPE=HOME:")) {
						String TelHome = s.substring(14);
						values.clear();
						values.put(Phone.RAW_CONTACT_ID, rawContactsId);
						values.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
						values.put(
								ContactsContract.CommonDataKinds.Phone.TYPE,
								ContactsContract.CommonDataKinds.Phone.TYPE_HOME);
						values.put(Phone.NUMBER, TelHome);
						getContentResolver().insert(Data.CONTENT_URI, values);
					} else if (s.startsWith("TEL;TYPE=CELL:")) {
						String TelCell = s.substring(14);
						values.clear();
						values.put(Phone.RAW_CONTACT_ID, rawContactsId);
						values.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
						values.put(
								ContactsContract.CommonDataKinds.Phone.TYPE,
								ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
						values.put(Phone.NUMBER, TelCell);
						getContentResolver().insert(Data.CONTENT_URI, values);
					} else if (s.startsWith("TEL;TYPE=WORK:")) {
						String TelWork = s.substring(14);
						values.clear();
						values.put(Phone.RAW_CONTACT_ID, rawContactsId);
						values.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
						values.put(
								ContactsContract.CommonDataKinds.Phone.TYPE,
								ContactsContract.CommonDataKinds.Phone.TYPE_WORK);
						values.put(Phone.NUMBER, TelWork);
						getContentResolver().insert(Data.CONTENT_URI, values);
					} else if (s.startsWith("EMAIL;TYPE=HOME:")) {
						String EmailHome = s.substring(15);
						values.clear();
						values.put(ContactsContract.Data.RAW_CONTACT_ID,
								rawContactsId);
						values.put(
								ContactsContract.Data.MIMETYPE,
								ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE);
						values.put(
								ContactsContract.CommonDataKinds.Email.TYPE,
								ContactsContract.CommonDataKinds.Email.TYPE_HOME);
						values.put(ContactsContract.CommonDataKinds.Email.DATA,
								EmailHome);
						getContentResolver().insert(
								ContactsContract.Data.CONTENT_URI, values);
					} else if (s.startsWith("EMAIL;TYPE=PREF:")) {
						String EmailWork = s.substring(15);
						values.clear();
						values.put(ContactsContract.Data.RAW_CONTACT_ID,
								rawContactsId);
						values.put(
								ContactsContract.Data.MIMETYPE,
								ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE);
						values.put(
								ContactsContract.CommonDataKinds.Email.TYPE,
								ContactsContract.CommonDataKinds.Email.TYPE_WORK);
						values.put(ContactsContract.CommonDataKinds.Email.DATA,
								EmailWork);
						getContentResolver().insert(
								ContactsContract.Data.CONTENT_URI, values);
					} else if (s.startsWith("EMAIL;TYPE=OTHER")) {
						String EmailOther = s.substring(16);
						values.clear();
						values.put(ContactsContract.Data.RAW_CONTACT_ID,
								rawContactsId);
						values.put(
								ContactsContract.Data.MIMETYPE,
								ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE);
						values.put(
								ContactsContract.CommonDataKinds.Email.TYPE,
								ContactsContract.CommonDataKinds.Email.TYPE_OTHER);
						values.put(ContactsContract.CommonDataKinds.Email.DATA,
								EmailOther);
						getContentResolver().insert(
								ContactsContract.Data.CONTENT_URI, values);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void exCalls() throws IOException {
		// TODO Auto-generated method stub
		String s = null;
		ContentValues values = new ContentValues();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream("/sdcard/backups.calls")));
			try {
				while ((s = br.readLine()) != null && s.startsWith("CALL:")) {
					String[] cateArr = s.substring(5, s.length()).split(",");
					values.put(CallLog.Calls.NUMBER, cateArr[0]);
					values.put(CallLog.Calls.DATE, cateArr[1]);
					values.put(CallLog.Calls.TYPE, cateArr[2]);
					values.put(CallLog.Calls.DURATION, cateArr[3]);
					getContentResolver().insert(CallLog.Calls.CONTENT_URI,
							values);
					values.clear();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// public void onClick(View v) {
	// // TODO Auto-generated method stub
	// switch (v.getId()) {
	// case R.id.btn1:
	// case R.id.btn2:
	// mViewPager.setCurrentItem(0);
	// break;
	// case R.id.btn3:
	// case R.id.btn4:
	// mViewPager.setCurrentItem(2);
	// break;
	// case R.id.saveBtn:
	// Toast toast = Toast.makeText(this, "saveBtn", Toast.LENGTH_SHORT);
	// toast.show();
	// break;
	// case R.id.exBtn:
	// Toast toast1 = Toast.makeText(this, "exBtn", Toast.LENGTH_SHORT);
	// toast1.show();
	// break;
	// }
	// }

	// /**
	// * A placeholder fragment containing a simple view.
	// */
	// public static class PlaceholderFragment extends Fragment {
	// /**
	// * The fragment argument representing the section number for this
	// * fragment.
	// */
	// private static final String ARG_SECTION_NUMBER = "section_number";
	//
	// /**
	// * Returns a new instance of this fragment for the given section number.
	// */
	// public static PlaceholderFragment newInstance(int sectionNumber) {
	// PlaceholderFragment fragment = new PlaceholderFragment();
	// Bundle args = new Bundle();
	// args.putInt(ARG_SECTION_NUMBER, sectionNumber);
	// fragment.setArguments(args);
	// return fragment;
	// }
	//
	// public PlaceholderFragment() {
	// }
	//
	// @Override
	// public View onCreateView(LayoutInflater inflater, ViewGroup container,
	// Bundle savedInstanceState) {
	// View rootView = inflater.inflate(R.layout.fragment_main, container,
	// false);
	// return rootView;
	// }
	// }

}
