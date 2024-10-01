package com.teclast_korea.teclast_qc_application.domain.qc_result

import com.teclast_korea.teclast_qc_application.ui.test_result.TestResultEvent
import com.teclast_korea.teclast_qc_application.ui.test_result.TestResultState

//FIXME 정상적인 첫 테스트 결과가 나중에 나오기는 함. 근데 꼭 한 번에 안 나오고 테스트를 두 세번 돌려야 나옴. 해당 이슈 찾아야 함. -> 해결
// Screen that allows the user to add a new contact to the database.

fun AddTestResult(
    state: TestResultState,/** Don't delete this parameter : It is used to detect Log and Error*/
    onEvent: (TestResultEvent) -> Unit,
    itemName: String = "",
    testResult: String= "",
    testDate: String = ""
) {
    // Update the TestResultEvent with information of the Test done by RPA
    onEvent(TestResultEvent.SetItemName(itemName))
    onEvent(TestResultEvent.SetTestResult(testResult))
    onEvent(TestResultEvent.SetTestDate(testDate))

    // Save the test result to the database
    onEvent(TestResultEvent.SaveTestResult)
    //Log.i("AddTestResult", "${state.testResults}")
}
