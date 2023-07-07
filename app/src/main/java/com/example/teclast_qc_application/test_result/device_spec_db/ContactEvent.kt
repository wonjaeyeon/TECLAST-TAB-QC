package com.example.teclast_qc_application.test_result.device_spec_db//package com.example.teclast_qc_application.test_result.room_db
//
//sealed interface ContactEvent2 {
//    object SaveContact: ContactEvent
//    data class SetFirstName(val firstName: String): ContactEvent
//    data class SetLastName(val lastName: String): ContactEvent
//    data class SetPhoneNumber(val phoneNumber: String): ContactEvent
//    object ShowDialog: ContactEvent
//    object HideDialog: ContactEvent
//    data class SortContacts(val sortType: SortType): ContactEvent
//    data class DeleteContact(val contact: Contact): ContactEvent
//}