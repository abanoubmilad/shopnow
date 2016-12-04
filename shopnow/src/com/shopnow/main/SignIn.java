package com.shopnow.main;

import android.app.Activity;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.shopnow.R;

public class SignIn extends Activity {
	private EditText email, password;
	private TextView signin, about, contact, help, create;
	private boolean validEmail, validPassword;
	private CheckBox remember;
	private ProgressBar pBar;

	private class SignInTask extends AsyncTask<String, Void, Integer> {

		@Override
		protected void onPreExecute() {
			pBar.setVisibility(View.VISIBLE);

		}

		@Override
		protected Integer doInBackground(String... params) {
			TaskManager.signIn(params[0], params[1]);
			return TaskManager.status;
		}

		@Override
		protected void onPostExecute(Integer status) {
			switch (status.intValue()) {
			case -1:
				Toast.makeText(getApplicationContext(), "json failed",
						Toast.LENGTH_SHORT).show();
				break;
			case 0:
				Intent in = new Intent(getApplicationContext(), SignIn.class);
				in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(in);
				finish();
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
				Toast.makeText(getApplicationContext(),
						"db error returned false", Toast.LENGTH_SHORT).show();
				break;
			case 7:
				Intent intent = new Intent(getApplicationContext(), Home.class);
				if (remember.isChecked()) {
					SharedPreferences.Editor editor = getSharedPreferences(
							"access", Context.MODE_PRIVATE).edit();
					editor.putString("email", email.getText().toString().trim());
					editor.putString("password", password.getText().toString()
							.trim());
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

			pBar.setVisibility(View.GONE);

		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);
		getActionBar().hide();
		pBar = (ProgressBar) findViewById(R.id.progress);

		SharedPreferences sharedPref = getSharedPreferences("access",
				Context.MODE_PRIVATE);
		if (sharedPref.getBoolean("remember", false)) {
			String email = sharedPref.getString("email", "");
			String password = sharedPref.getString("password", "");
			if (!Validator.isEmail(email) || password.length() < 6)
				sharedPref.edit().clear().commit();
			else
				new SignInTask().execute(email, password);

		}

		email = (EditText) findViewById(R.id.email);
		password = (EditText) findViewById(R.id.pass);

		help = (TextView) findViewById(R.id.help);
		contact = (TextView) findViewById(R.id.contact);
		about = (TextView) findViewById(R.id.about);

		signin = (TextView) findViewById(R.id.sign_in);
		create = (TextView) findViewById(R.id.create);

		remember = (CheckBox) findViewById(R.id.check);

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
				if (validEmail && validPassword)
					signin.setBackgroundColor(getResources().getColor(
							R.color.blue));
				else
					signin.setBackgroundColor(getResources().getColor(
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
				if (validPassword && validEmail)
					signin.setBackgroundColor(getResources().getColor(
							R.color.blue));
				else
					signin.setBackgroundColor(getResources().getColor(
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
				startActivity(new Intent(getApplicationContext(), SignUp.class));
			}
		});
		signin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// if (!validEmail)
				// email.setError("Ops, this does't seem to be a valid email.");
				// else if (!validPassword)
				// password.setError("Ops, password must be at least 6 characters in length.");
				// else
				if (ConnectManager.isNetworkAvailable(getApplicationContext()))
					new SignInTask().execute("user@mail.num0", "passmepassme");
				// Connect.signIn(email.getText().toString().trim(), password
				// .getText().toString().trim());
				// startActivity(new Intent(getApplicationContext(),
				// Home.class));
			}
		});
	}
}
