package com.example.aikinow

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.FirebaseException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_register.*
import java.util.concurrent.TimeUnit


class RegisterActivity : AppCompatActivity() {

    lateinit var phoneText: EditText
    lateinit var codeText: EditText
    lateinit var continueBtn: Button
    private var checker: String? = null
    private var phoneNumber: String? = null
    lateinit var relativeLayout: RelativeLayout

    lateinit var mResendToken: PhoneAuthProvider.ForceResendingToken
    lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    lateinit var mAuth: FirebaseAuth
    lateinit var mVerificationId: String
    lateinit var loadingBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        phoneText = findViewById(R.id.phoneText)
        codeText = findViewById(R.id.codeText)
        continueBtn = findViewById(R.id.continueBtn)

        loadingBar = findViewById(R.id.progressBar)


        continueBtn.setOnClickListener {view:View ->
            progressBar.visibility=View.VISIBLE
            sendVerificationCode()
            phoneText.visibility=View.GONE
            continueBtn.visibility = View.GONE
           

            Toast.makeText(this, "Verificaction Code sent", Toast.LENGTH_LONG).show()
        }
        authenticateBtn.setOnClickListener {view:View ->
                progressBar.visibility =View.GONE
                verifySignInCode()
        }

    }
    private fun verificationCallBack(){
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                progressBar.visibility=View.INVISIBLE
                signInWithPhoneAuthCredential(credential)


            }


            override fun onVerificationFailed(e: FirebaseException) {

            }

            override fun onCodeSent(
                verificationId: String, token: PhoneAuthProvider.ForceResendingToken
            ) {

                super.onCodeSent(verificationId, token)
                mVerificationId = verificationId.toString()


            }
        }
    }
    private fun sendVerificationCode() {

        verificationCallBack()
        phoneNumber = phoneText.text.toString()
//        if (phoneNumber!!.isEmpty()) {
//            phoneText.editText!!.setError("phone number is required")
//            phoneText.requestFocus()
//            return
//        }
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phoneNumber!!, // Phone number to verify
            60, // Timeout duration
            TimeUnit.SECONDS, // Unit of timeout
            this, // Activity (for callback binding)
            callbacks
        ) // OnVerificationStateChangedCallbacks

    }

    private fun verifySignInCode(){
        var code:String = codeText.text.toString()
        val credential = PhoneAuthProvider.getCredential(mVerificationId, code)
        signInWithPhoneAuthCredential(credential)

}
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                    Toast.makeText(this, "you are logged in", Toast.LENGTH_LONG).show()

                } else {
                    // Sign in failed, display a message and update the UI d
                    }
                }
            }
    }


//    private fun verificationCallbacks(){
//        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//
//            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
//                loadingBar.visibility = View.INVISIBLE
//                val code: String? = credential.getSmsCode()
//                if (code != null) {
//                    codeText.editText!!.setText(code);
//                    //verifying the code
//                    verifyVerificationCode(code);
//                }
//
//
//
//            }
//
//            override fun onVerificationFailed(e: FirebaseException) {
//
//            }
//
//            override fun onCodeSent(
//                verificationId: String,
//                token: PhoneAuthProvider.ForceResendingToken
//            ) {
//
//                super.onCodeSent(verificationId, token )
//                mVerificationId = verificationId
//                phoneText.visibility= View.GONE
//                codeText.visibility = View.VISIBLE
//
//
//
//            }
//        }
//    }
//    private fun verifyVerificationCode(code: String) { //creating the credential
//        val credential = PhoneAuthProvider.getCredential(mVerificationId, code)
//        //signing the user
//        signInWithPhoneAuthCredential(credential)
//    }
//
//    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
//        mAuth.signInWithCredential(credential)
//            .addOnCompleteListener(this) { task: Task<AuthResult> ->
//                if (task.isSuccessful) {
//                    Toast.makeText(this, "youve succesfully logged in", Toast.LENGTH_LONG).show()
//                    startActivity(Intent(this, AnotherActivity::class.java))
//                } else {
//
//                    }
//                }
//            }
//    private fun verify(){
//        verificationCallbacks()
//
//        phoneNumber= phoneText.editText?.text.toString()
//        PhoneAuthProvider.getInstance().verifyPhoneNumber(
//            phoneNumber!!, // Phone number to verify
//            60, // Timeout duration
//            TimeUnit.SECONDS, // Unit of timeout
//            this, // Activity (for callback binding)
//            callbacks) // OnVerificationStateChangedCallbacks
//    }



