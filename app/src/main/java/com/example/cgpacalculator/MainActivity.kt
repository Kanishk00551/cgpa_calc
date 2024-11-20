package com.example.cgpacalculator

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cgpacalculator.ui.theme.CGPAcalculatorTheme

data class Semester(val grade: String,val credit : Int){}
private var semesters : MutableList<Semester> = mutableListOf()
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CGPAcalculatorTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = TODO(),
                    bottomBar = TODO(),
                    snackbarHost = TODO(),
                    floatingActionButton = TODO(),
                    floatingActionButtonPosition = TODO(),
                    containerColor = TODO(),
                    contentColor = TODO(),
                    contentWindowInsets = TODO(),
                    content = TODO()


                )
                CGPA(semesters)


            }
        }
    }
}

@Composable

fun CGPA( semesters : MutableList<Semester> ) {
    var grade1 by remember{
        mutableStateOf("")
    }
    var credit1 by remember{
        mutableStateOf<Int?>(null)
    }
    var grade2 by remember{
        mutableStateOf("")
    }
    var credit2 by remember{
        mutableStateOf<Int?>(null)
    }
    var grade3 by remember{
        mutableStateOf("")
    }
    var credit3 by remember{
        mutableStateOf<Int?>(null)
    }
    var grade4 by remember{
        mutableStateOf("")
    }
    var credit4 by remember{
        mutableStateOf<Int?>(null)
    }
    var cgpa by remember { mutableStateOf( 0.0) }
    Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {
        Text(
            "CGPA CALCULATOR \nAapka Anka , Apka Bhavishya",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center),
            color = Color(0xFF000000)
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))

        SubjectText(Subject = "Subject1")
        GradeTextField(grade1) {grade1=it}
        Spacer8dp()
        CreditTextField(credit1){credit1=it}

        SubjectText(Subject = "Subject2")
        GradeTextField(grade2) {grade2=it}
        Spacer8dp()
        CreditTextField(credit2)  {credit2=it}

        SubjectText(Subject = "Subject3")
        GradeTextField(grade3) {grade3=it}
        Spacer8dp()
        CreditTextField(credit3)  {credit3=it}

        SubjectText(Subject = "Subject4")
        GradeTextField(grade4) {grade4=it}
        Spacer8dp()
        CreditTextField(credit4)  {credit4=it}

        Spacer8dp()
        Row (){ Column(

        ){
            Button(onClick = {
                val semester=Semester(grade1,credit1 ?:0)
                semesters.add(semester)
                val totalCredit=semesters.sumOf { it.credit }
                val totalGradePoint=semesters.sumOf { CalculateGradePoints(it.grade , it.credit) }
                if (totalCredit>0){
                    cgpa = totalGradePoint/totalCredit.toDouble()
                }else {
                    cgpa = 0.0
                }

                 grade1=""
                credit1=null

                grade2=""
                credit2=null
                grade3=""
                credit3=null
                grade4=""
                credit4=null

            }){
                Text("Calculate Cgpa" , fontSize = 16.sp)

                }
            Surface (modifier=Modifier.width(160.dp).wrapContentHeight(), color = Color(0xFF263238)
            , shape = RoundedCornerShape(15.dp)
            ){
                Text(modifier = Modifier.padding(start =10.dp) ,
                    text="All Time \n CGPA $cgpa " , style = TextStyle(
                    fontSize = 16.sp, color = Color.White
                ))
            }
            }
            Surface (modifier=Modifier.fillMaxSize().padding(start=15.dp), color = Color(0xFF263238)
                , shape = RoundedCornerShape(15.dp)
            ){
                Column (){
                Text(modifier = Modifier.padding(start =10.dp) ,textAlign = TextAlign.Center,
                    text="Previous Sem CGPA " , style = TextStyle(
                        fontSize = 16.sp, color = Color.White
                    )
                )
                    if(semesters.isNotEmpty()){
                        for (semester in semesters) {
                            Text(
                                modifier = Modifier.fillMaxWidth().padding(start = 10.dp),
                                textAlign = TextAlign.Center,
                                text = "Grade :  ${semester.grade} ,  Credit ${semester.credit}",
                                color = Color.White,
                                fontSize = 16.sp
                            )
                        }
                }
                }
            }
        }

    }
}


fun CalculateGradePoints(grade: String, credit: Int) : Double {
    return when(grade){
        "A"->4.0
        "a"->4.0
        "B"->3.0
        "b"->3.0
        "C"->2.0
        "c"->2.0
        "D"->1.0
        "d"->1.0
        else -> 0.0

    } * credit
}

@Composable
fun Spacer8dp(){
    Spacer( modifier=Modifier.padding(8.dp))
}


@Composable
fun SubjectText(Subject: String) {
    Text(
        text = "Subject",
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(fontSize = 20.sp),
        color = Color(0xFF000000)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradeTextField(grade: String, onValueChange: (String) -> Unit) {
    TextField(
        value = grade, onValueChange = { text -> onValueChange(text) },
        modifier = Modifier
            .fillMaxWidth()
            .height(47.dp),
        label = {
            Text(
                text = "Enter Grade", color = Color.Black,
                fontSize = 12.sp
            )
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Color(0xFF7E57C2),
        ), shape = RoundedCornerShape(15.dp),
        textStyle = TextStyle(fontSize = 12.sp, color = Color.Black)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreditTextField(credit: Int?, onValueChange: (Int?) -> Unit) {
    TextField(
        value = credit.toString(), onValueChange = { text -> onValueChange(text.toIntOrNull()) },
        modifier = Modifier
            .fillMaxWidth()
            .height(47.dp),
        label = {
            Text(
                text = "Enter credit", color = Color.Black,
                fontSize = 12.sp
            )
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Color(0xFF7d8CCED),
        ), shape = RoundedCornerShape(15.dp),
        textStyle = TextStyle(fontSize = 12.sp, color = Color.Black)
    )
}


@Preview(showBackground = true)
@Composable
fun CGPApreview() {
    CGPAcalculatorTheme {
        CGPA(semesters)
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GradeTextField(){
//    CGPAcalculatorTheme {
//        GradeTextField()
//    }
//}