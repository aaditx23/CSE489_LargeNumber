package com.cse489.largernumber

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableOpenTarget
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cse489.largernumber.ui.theme.*


import kotlin.random.Random


@Composable
fun Screen(){

    var r1 by remember { mutableStateOf(Random.nextInt(100)) }
    var r2 by remember { mutableStateOf(Random.nextInt(100)) }
    var score by remember { mutableStateOf(0) }
    var clicks by remember { mutableStateOf(0) }
    Card(
        colors = CardDefaults.cardColors(HintOfGreen),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp, bottom = 50.dp)
            .border(
                width = 2.dp,
                color = Color.Transparent
            )
    ) {
        Title()

        Score(score)

        ButtonRow(
            t1 = r1,
            t2 = r2,
            onClick = {a, b ->
                if (a>b){
                    score++
                }
                else{
                    if (score >0 ){
                        score--
                    }
                }
                r1 = Random.nextInt(100)
                r2 = Random.nextInt(100)
                clicks++
            }
        )

        Clicks(click = clicks)

        Reset({
            score = 0
            clicks = 0
        })

    }
}

@Composable
fun Title(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp)
    ) {
        Text(
            text = "Click the Larger Number",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Cursive
        )
    }
}

@Composable
fun ButtonRow(t1: Int, t2: Int, onClick :(Int, Int) -> Unit){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 150.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        Column {
            Button(
                onClick = { onClick(t1, t2) },
                modifier = Modifier.padding(end = 200.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Pink40)
            ) {
                Text(text = t1.toString())
            }
        }
        Column {
            Button(
                onClick = { onClick(t2, t1) },
                colors = ButtonDefaults.buttonColors(containerColor = Purple40)
            ) {
                Text(text = t2.toString())
            }
        }

    }
}


@Composable
fun Score(score : Int){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 100.dp),
        horizontalArrangement = Arrangement.Center

    ) {
        Text(text = "Score: $score")
    }
}

@Composable
fun Clicks(click: Int){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 200.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = "Tried $click times!")
    }
}

@Composable
fun Reset(onClick : () -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Column {
            ElevatedButton(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(containerColor = PaleRed),

                ) {
                Text(text = "Reset")
            }
        }
    }
}