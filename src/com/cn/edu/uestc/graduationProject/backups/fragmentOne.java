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
public class fragmentOne extends Fragment {

	private oneListener mListener;
	private RadioButton Brecord, Bphone, Bsms;
	private ImageButton saveBtn;
	private int jud = -1;

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_one, container,
				false);
		return rootView;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Brecord = (RadioButton) getActivity().findViewById(R.id.Brecord);
		Bphone = (RadioButton) getActivity().findViewById(R.id.Bphone);
		Bsms = (RadioButton) getActivity().findViewById(R.id.Bsms);
		saveBtn = (ImageButton) getActivity().findViewById(R.id.saveBtn);
		saveBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (Brecord.isChecked())
					jud = 0;
				else if (Bphone.isChecked())
					jud = 1;
				else if (Bsms.isChecked())
					jud = 2;
				else
					jud = -1;
				mListener.onOneListener(jud);
			}

		});
	}

	public interface oneListener {
		public void onOneListener(int jud);
	}

	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (oneListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement oneListener");
		}
	}
}
