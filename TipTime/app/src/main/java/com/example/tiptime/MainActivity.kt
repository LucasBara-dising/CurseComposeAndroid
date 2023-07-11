package com.example.tiptime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiptime.ui.theme.TipTimeTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TipTimeScreen()
                }
            }
        }
    }
}

//Incluir elemntos de interface
@Composable
fun TipTimeScreen(modifier: Modifier = Modifier) {
    var amountInput by remember { mutableStateOf("") }
    var tipInput by remember{ mutableStateOf("") }

    //define estado padrão do Switch
    var roundUp by remember { mutableStateOf(false) }

    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val tipPercent  = tipInput.toDoubleOrNull() ?: 0.0

    //quem calcula a porcentagem
    val tip = calculateTip(amount,tipPercent, roundUp)

    val focusManager= LocalFocusManager.current

    Column(
        modifier.padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)) {

        Text(
            text = stringResource(R.string.calculate_tip),
            fontSize = 24.sp,
            modifier=Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier.height(16.dp))

        EditNumberField(
            label=R.string.bill_amount,
            keyboardOptions=KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,//teclado tipo numerico
                imeAction = ImeAction.Next),//btn ação pro proxima caixa
            keyboardActions = KeyboardActions(
                onNext = {focusManager.moveFocus(FocusDirection.Down)}
            ),
            value = amountInput,
            onValueChange = { amountInput = it }
        )

        Spacer(modifier.height(16.dp))

        EditNumberField(
            label=R.string.how_was_the_service,
            keyboardOptions =KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number, //teclado tipo numerico
                imeAction = ImeAction.Done //btn ação pra enviar
            ),
            keyboardActions = KeyboardActions(
                onDone = {focusManager.clearFocus()}
            ),
            value = tipInput,
            onValueChange = {tipInput=it}
        )

        Spacer(modifier.height(16.dp))

        RoundTheTipRow(roundUp = roundUp, onRoundUpChanged = {roundUp=it})

        Spacer(modifier.height(16.dp))

        Text(
            text = stringResource(id = R.string.tip_amount, tip),
            modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNumberField(
        @StringRes label:Int,
        value:String,
        keyboardOptions: KeyboardOptions,
        keyboardActions: KeyboardActions,
        onValueChange:(String)->Unit,
        modifier: Modifier = Modifier)
{

    TextField(
        value = value,
        modifier = Modifier.fillMaxWidth(),//pega tela toda
        singleLine = true,//permite so uma linha
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        label = { Text(stringResource(label))},//label
        onValueChange = onValueChange//atualiza o valor
   )
}

@Composable
fun RoundTheTipRow(
    roundUp: Boolean,
    onRoundUpChanged:(Boolean)->Unit,
    modifier: Modifier=Modifier
){
    Row(
        modifier= modifier
            .fillMaxWidth()
            .size(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ){

        Text(text = stringResource(R.string.round_up_tip))

        Switch(
            checked = roundUp,
            onCheckedChange = onRoundUpChanged,
            colors=SwitchDefaults.colors(uncheckedThumbColor = Color.DarkGray),
            modifier= modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
        )
    }
}

@VisibleForTesting
fun calculateTip(
    amount: Double,
    tipPercent: Double = 15.0,
    roundUp: Boolean
):String {

    var tip = tipPercent / 100 * amount

    if(roundUp){
        tip= kotlin.math.ceil(tip)
    }
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TipTimeTheme {
        TipTimeScreen()
    }
}