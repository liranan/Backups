package com.cn.edu.uestc.graduationProject.backups;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * A placeholder fragment containing a simple view.
 */
public class fragmentMain extends Fragment implements OnClickListener {

	private twoListener mListener;
	private ImageButton btn1, btn3;
	private Button btn2, btn4;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container,
				false);
		return rootView;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		btn1 = (ImageButton) getActivity().findViewById(R.id.btn1);
		btn1.setOnClickListener(this);
		btn2 = (Button) getActivity().findViewById(R.id.btn2);
		btn2.setOnClickListener(this);
		btn3 = (ImageButton) getActivity().findViewById(R.id.btn3);
		btn3.setOnClickListener(this);
		btn4 = (Button) getActivity().findViewById(R.id.btn4);
		btn4.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn1:
		case R.id.btn2:
			mListener.onTwoListener(0);
			break;
		case R.id.btn3:
		case R.id.btn4:
			mListener.onTwoListener(2);
			break;
		}
	}

	public interface twoListener {
		public void onTwoListener(int a);
	}

	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (twoListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement twoListener");
		}
	}
}