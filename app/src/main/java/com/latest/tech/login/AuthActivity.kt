package com.latest.tech.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.latest.tech.R
import com.latest.tech.components.TopBar

class AuthActivity : ComponentActivity() {


    private lateinit var authViewModel: AuthViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //binding
//        createBindings()

        //compose
        setContent {
            val pwdState = remember {
                mutableStateOf("")
            }
            val pwdError = remember {
                mutableStateOf(false)
            }
            val userError = remember {
                mutableStateOf(false)
            }
            val userState = remember { mutableStateOf("") }

            Surface(modifier = Modifier.fillMaxSize()) {

                Column() {

                    TopBar(title = { Text("Test App!") })
                    authView(pwdState, userError, pwdError, userState)
                }
            }
        }

        authViewModel =
            ViewModelProvider(
                this,
                AuthViewModelFactory(this@AuthActivity)
            )[AuthViewModel::class.java]

        //error handling

    }

    /* private fun createBindings() {
         binding = ActivityAuthBinding.inflate(layoutInflater)
         setContentView(binding.root)

         val userName = binding.username
         val password = binding.password
         val login = binding.login
         val loading = binding.loading

         val authViewModel =
             ViewModelProvider(this, AuthViewModelFactory())[AuthViewModel::class.java]


         //error handling
         authViewModel.authFormState.observe(this) {

             login.isEnabled = it.isDataValid
             userName.error = it.userNameError?.let { it1 -> getString(it1) }
             password.error = it.passwordError?.let { it1 -> getString(it1) }
         }


         //text change listener
         userName.onTextChange {
             authViewModel.dataChange(userName.text.toString(), null)
         }
         password.onTextChange {
             authViewModel.dataChange(null, password.text.toString())
         }

         //click listener
         login.setOnClickListener {

             loading.visibility = View.VISIBLE
             authViewModel.authenticate(
                 userName = userName.text.toString(), password = password.text.toString()
             )
         }

     }*/


    @Composable
    fun authView(
        pwdState: MutableState<String>,
        userError: MutableState<Boolean>,
        pwdError: MutableState<Boolean>,
        userState: MutableState<String>
    ) {

        authViewModel.authFormState.observe(this) {

            userError.value = !it.isUserValid
            pwdError.value = !it.isPwdValid
        }


        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxHeight()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Text(
                    text = "LOGIN SCREEN",
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                )

                LoginFields(userState, userError, pwdError, pwdState)

                Button(modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .padding(start = 40.dp, end = 40.dp),
                    enabled = !(pwdError.value || userError.value),
                    onClick = {
                        authViewModel.authenticate(
                            userName = userState.toString(), password = pwdState.toString()
                        )
                    }) {
                    Text(
                        text = "Login",
                        fontSize = 17.sp
                    )
                }
            }
        }
    }

    @Composable
    fun LoginFields(
        userState: MutableState<String>,
        userError: MutableState<Boolean>,
        pwdError: MutableState<Boolean>,
        pwdState: MutableState<String>
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        TextField(
            value = userState.value,
            onValueChange = {
                userState.value = it
                authViewModel.dataChange(it, null)

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 50.dp, end = 50.dp),
            label = { Text(text = "Enter UserName") },
            isError = userError.value,
            singleLine = true,
            supportingText = {
                if (userError.value) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.invalid_username),
                        color = Color.Red
                    )
                }
            },
            trailingIcon = {
                if (userError.value)
                    Icon(
                        Icons.Rounded.Warning,
                        "error",
                        tint = Color.Red
                    )
            },
        )
        Spacer(modifier = Modifier.height(20.dp))


        TextField(
            value = pwdState.value,
            onValueChange = {
                pwdState.value = it
                authViewModel.dataChange(null, it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 50.dp, end = 50.dp),
            label = { Text(text = "Enter Password") },
            isError = pwdError.value,
            singleLine = true,
            supportingText = {
                if (pwdError.value) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.invalid_password),
                        color = Color.Red
                    )
                }
            },
            trailingIcon = {
                if (pwdError.value)
                    Icon(
                        Icons.Rounded.Warning,
                        "error",
                        tint = Color.Red
                    )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
        )

        Spacer(modifier = Modifier.height(50.dp))


    }

    @Composable
    @Preview(showSystemUi = true)
    fun previewAuthView() {
//        authView(pwdState, pwdError, userState)
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */

fun EditText.onTextChange(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(p0: Editable?) {
            afterTextChanged.invoke(p0.toString())
        }

    })


}
