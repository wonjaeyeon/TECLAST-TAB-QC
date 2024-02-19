package com.example.teclast_qc_application.test_result.test_results_db

import android.util.Log

//FIXME 정상적인 첫 테스트 결과가 나중에 나오기는 함. 근데 꼭 한 번에 안 나오고 테스트를 두 세번 돌려야 나옴. 해당 이슈 찾아야 함.
// Screen that allows the user to add a new contact to the database.

fun AddTestResultV2(
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit,
    itemName: String = "",
    testResult: String= "",
    testDate: String = ""
) {

    //onEvent(TestResultEvent.StartTest)
    onEvent(TestResultEvent.SetItemName(itemName))
    onEvent(TestResultEvent.SetTestResult(testResult))
    onEvent(TestResultEvent.SetTestDate(testDate))

    onEvent(TestResultEvent.SaveTestResult)
    //onEvent(TestResultEvent.EndTest)
    Log.i("AddTestResultV2", "${state.testResults}")
}



//        buttons = {
//            Box(
//                modifier = Modifier.fillMaxWidth(),
//                contentAlignment = Alignment.CenterEnd
//            ) {
//                Button(onClick = {
//                    onEvent(TestResultEvent.SaveTestResult)
//                }) {
//                    Text(text = "Save")
//                }
//            }
//        }
