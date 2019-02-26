package com.example.logonrmlocal.projectpokemon


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

import kotlinx.android.synthetic.main.activity_login.*

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {

    private  val CADASTRO_REQUEST_CODE = 1

    private  lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Set up the login form.

        mAuth  = FirebaseAuth.getInstance()


        if(mAuth.currentUser != null){
            vaiparateladoMenu()

        }

        btLogar.setOnClickListener {
            mAuth.signInWithEmailAndPassword(
                    etEmail.text.toString(),
                    etSenha.text.toString()

            ).addOnCompleteListener {

                if(it.isSuccessful){
                    vaiparateladoMenu()
                }else{
                    Toast.makeText(this@LoginActivity, it.exception?.message, Toast.LENGTH_LONG).show()
                }
            }

        }


        btNovaConta.setOnClickListener({
            val telaSeguite = Intent(this, SignUpActivity::class.java)
            startActivityForResult(telaSeguite, CADASTRO_REQUEST_CODE)
        })


    }



    private  fun    vaiparateladoMenu(){
        val telaSeguite = Intent(this, MenuActivity::class.java)
        startActivity(telaSeguite)
        finish()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            CADASTRO_REQUEST_CODE ->{
                when(resultCode){
                    Activity.RESULT_OK ->{
                        etEmail.setText(data?.getStringExtra("email"))
                        etSenha.setText(data?.getStringExtra("senha"))
                    }
                }
            }
        }
    }
}
