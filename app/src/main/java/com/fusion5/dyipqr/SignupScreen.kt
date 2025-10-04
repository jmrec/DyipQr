package com.fusion5.dyipqr

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun SignupScreen(navController: NavHostController) {
	var firstName by rememberSaveable { mutableStateOf("") }
	var lastName by rememberSaveable { mutableStateOf("") }
	var email by rememberSaveable { mutableStateOf("") }
	var password by rememberSaveable { mutableStateOf("") }
	var passwordVisible by rememberSaveable { mutableStateOf(false) }
	var termsAccepted by rememberSaveable { mutableStateOf(false) }

	CommonScreenLayout(
		title = "Register",
		onBack = { navController.popBackStack() }
	) {
		Column(
			modifier = Modifier.fillMaxSize(),
			verticalArrangement = Arrangement.Center
		) {
			Text(
				text = "Hello there,",
				style = MaterialTheme.typography.bodyLarge
			)
			Text(
				text = "Create an Account",
				style = MaterialTheme.typography.headlineMedium
			)
			Spacer(modifier = Modifier.height(30.dp))

			OutlinedTextField(
				value = firstName,
				onValueChange = { firstName = it },
				modifier = Modifier.fillMaxWidth(),
				label = { Text("First Name") },
				leadingIcon = {
					Icon(
						Icons.Outlined.Person,
						contentDescription = "First Name Icon"
					)
				},
				singleLine = true
			)
			Spacer(modifier = Modifier.height(16.dp))

			OutlinedTextField(
				value = lastName,
				onValueChange = { lastName = it },
				modifier = Modifier.fillMaxWidth(),
				label = { Text("Last Name") },
				leadingIcon = {
					Icon(
						Icons.Outlined.Person,
						contentDescription = "Last Name Icon"
					)
				},
				singleLine = true
			)
			Spacer(modifier = Modifier.height(16.dp))

			OutlinedTextField(
				value = email,
				onValueChange = { email = it },
				modifier = Modifier.fillMaxWidth(),
				label = { Text("Email") },
				leadingIcon = {
					Icon(
						Icons.Outlined.Email,
						contentDescription = "Email Icon"
					)
				},
				keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
				singleLine = true
			)
			Spacer(modifier = Modifier.height(16.dp))

			OutlinedTextField(
				value = password,
				onValueChange = { password = it },
				modifier = Modifier.fillMaxWidth(),
				label = { Text("Password") },
				leadingIcon = {
					Icon(
						Icons.Outlined.Lock,
						contentDescription = "Password Icon"
					)
				},
				keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
				singleLine = true,
				visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
				trailingIcon = {
					val image =
						if (passwordVisible) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility
					val description = if (passwordVisible) "Hide password" else "Show password"
					IconButton(onClick = { passwordVisible = ! passwordVisible }) {
						Icon(
							imageVector = image,
							description
						)
					}
				}
			)
			Spacer(modifier = Modifier.height(16.dp))

			Row(
				verticalAlignment = Alignment.CenterVertically,
				modifier = Modifier.fillMaxWidth()
			) {
				Checkbox(
					checked = termsAccepted,
					onCheckedChange = { termsAccepted = it }
				)
				Spacer(modifier = Modifier.width(8.dp))
				Text(
					text = "I accept the terms and conditions.",
					style = MaterialTheme.typography.bodySmall
				)
			}
			Spacer(modifier = Modifier.height(30.dp))

			Button(
				onClick = {
					// TODO: Handle signup logic
					// For now, can navigate to login or show a message
					// navController.navigate(AppDestinations.LOGIN_ROUTE)
				},
				modifier = Modifier.fillMaxWidth(),
				enabled = termsAccepted // Button enabled only if terms are accepted
			) {
				Text("Register")
			}
			Spacer(modifier = Modifier.height(16.dp))

			val annotatedText = buildAnnotatedString {
				append("Already have an account? ")
				withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
					pushStringAnnotation(
						tag = "LOGIN",
						annotation = AppDestinations.LOGIN_ROUTE
					) // Use AppDestinations
					append("Login")
					pop()
				}
			}

			ClickableText(
				text = annotatedText,
				onClick = { offset ->
					annotatedText.getStringAnnotations(
						tag = "LOGIN",
						start = offset,
						end = offset
					)
						.firstOrNull()?.let {
							navController.navigate(it.item) { // Use it.item which is AppDestinations.LOGIN_ROUTE
								popUpTo(navController.graph.startDestinationId) { inclusive = true }
							}
						}
				},
				modifier = Modifier.align(Alignment.CenterHorizontally)
			)
		}
	}
}
