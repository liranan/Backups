package com.cn.edu.uestc.graduationProject.backups;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;

/**
 * A placeholder fragment containing a simple view.
 */
public class fragmentThree extends Fragment {

	private threeListener mListener;
	private RadioButton Rrecord, Rphone, Rsms;
	private ImageButton exBtn;
	private int jud;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_three, container,
				false);
		return rootView;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Rrecord = (RadioButton) getActivity().findViewById(R.id.Rrecord);
		Rphone = (RadioButton) getActivity().findViewById(R.id.Rphone);
		Rsms = (RadioButton) getActivity().findViewById(R.id.Rsms);
		// File csvFile = new File("mnt/sdcard", "backups.vcf");
		// File smsFile = new File("mnt/sdcard", "backups.sms");
		// File callsFile = new File("mnt/sdcard", "backups.calls");
		// Rrecord.setClickable(csvFile.exists());
		// Rphone.setClickable(smsFile.exists());
		// Rsms.setClickable(callsFile.exists());
		exBtn = (ImageButton) getActivity().findViewById(R.id.exBtn);
		exBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (Rrecord.isChecked())
					jud = 0;
				else if (Rphone.isChecked())
					jud = 1;
				else if (Rsms.isChecked())
					jud = 2;
				else
					jud = -1;
				mListener.onThreeListener(jud);
			}

		});
	}

	public interface threeListener {
		public void onThreeListener(int jud);
	}

	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (threeListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement threeListener");
		}
	}
}
