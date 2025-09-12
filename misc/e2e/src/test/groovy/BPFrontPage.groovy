package com.nerdyninja.college.bp

import geb.Page

class BPFrontPage extends Page {
    static at = { title.startsWith("BP Category Calculator") }
    static url = "/"

    static content = {
        submitButton(to: BPFrontPage) { $("input", type: "submit") }
        systolic { $("input", name: "systolic") }
        systolic_error { $("span", id: "systolic-error") }
        diastolic { $("input", name: "diastolic") }
        diastolic_error { $("span", id: "diastolic-error") }
        age_select { $('select', id:'agerange') }
        email { $("input", name: "email") }
        email_error { $("span", id: "email-error") }
        result { $("label",id: "result")}
    }
}
