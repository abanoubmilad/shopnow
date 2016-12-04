package com.shopnow.main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shopnow.R;

public class SignUp extends Activity {
	private EditText name, email, password, password2;
	private TextView signin, about, contact, help, create;
	private boolean validName, validEmail, validPassword, passwordMatch;
	private CheckBox remember;

	private class SignUpTask extends AsyncTask<Void, Void, Integer> {
		private ProgressDialog pBar;

		@Override
		protected void onPreExecute() {
			pBar = new ProgressDialog(SignUp.this);
			pBar.setCancelable(false);
			pBar.setTitle(R.string.msg_progress_loading);
			pBar.setMessage(getResources().getString(R.string.msg_progress_signup));
			pBar.show();
		}

		@Override
		protected Integer doInBackground(Void... params) {
			TaskManager.signUp(name.getText().toString().trim(), email
					.getText().toString().trim(), password.getText().toString()
					.trim());
			return TaskManager.status;

		}

		@Override
		protected void onPostExecute(Integer status) {
			switch (status.intValue()) {
			case -1:
				Toast.makeText(getApplicationContext(), "json failed",
						Toast.LENGTH_SHORT).show();
				break;
			case 1:
				Toast.makeText(getApplicationContext(), "data not sent",
						Toast.LENGTH_SHORT).show();
				break;
			case 2:
				Toast.makeText(getApplicationContext(), "invalid params",
						Toast.LENGTH_SHORT).show();
				break;
			case 3:
				Toast.makeText(getApplicationContext(), "db error returned false",
						Toast.LENGTH_SHORT).show();
				break;
			case 7:
				Intent intent = new Intent(getApplicationContext(), Home.class);
				if (remember.isChecked()) {
					SharedPreferences.Editor editor = getSharedPreferences(
							"access", Context.MODE_PRIVATE).edit();
					editor.putString("email", email.getText().toString().trim());
					editor.putString("password", password.getText().toString()
							.trim());
					editor.putBoolean("remember", true);
					editor.commit();
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
							| Intent.FLAG_ACTIVITY_CLEAR_TASK);
				}
				startActivity(intent);
				finish();
				break;
			default:
				break;
			}
			pBar.dismiss();

		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		getActionBar().hide();

		email = (EditText) findViewById(R.id.email);
		name = (EditText) findViewById(R.id.name);
		password = (EditText) findViewById(R.id.pass1);
		password2 = (EditText) findViewById(R.id.pass2);

		help = (TextView) findViewById(R.id.help);
		contact = (TextView) findViewById(R.id.contact);
		about = (TextView) findViewById(R.id.about);

		signin = (TextView) findViewById(R.id.sign_in);
		create = (TextView) findViewById(R.id.create);

		remember = (CheckBox) findViewById(R.id.check);

		name.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				validName = s.toString().trim().length() > 3;
				if (validName && validEmail && validPassword && passwordMatch)
					create.setBackgroundColor(getResources().getColor(
							R.color.blue));
				else
					create.setBackgroundColor(getResources().getColor(
							R.color.lgrey));
			}
		});
		email.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				validEmail = Validator.isEmail(s.toString().trim());
				if (validEmail && validName && validPassword && passwordMatch)
					create.setBackgroundColor(getResources().getColor(
							R.color.blue));
				else
					create.setBackgroundColor(getResources().getColor(
							R.color.lgrey));
			}
		});
		password.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				validPassword = s.toString().trim().length() > 5;
				if (validPassword && validName && validEmail && passwordMatch)
					create.setBackgroundColor(getResources().getColor(
							R.color.blue));
				else
					create.setBackgroundColor(getResources().getColor(
							R.color.lgrey));
			}
		});

		password2.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				passwordMatch = s.toString().matches(
						password.getText().toString());
				if (passwordMatch && validName && validEmail && validPassword)
					create.setBackgroundColor(getResources().getColor(
							R.color.blue));
				else
					create.setBackgroundColor(getResources().getColor(
							R.color.lgrey));

			}
		});
		help.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

		contact.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

		about.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

		create.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!validName)
					name.setError("Ops, name must be at least 4 characters in length.");
				else if (!validEmail)
					email.setError("Ops, this does't seem to be a valid email.");
				else if (!validPassword)
					password.setError("Ops, password must be at least 6 characters in length.");
				else if (!passwordMatch)
					password2.setError("Ops, password not matching.");
				else if (ConnectManager.isNetworkAvailable(getApplicationContext()))
						new SignUpTask().execute();
			}
		});
		signin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), SignIn.class));
				finish();
			}
		});
	}
}
