package com.ujjallamichhane.week6assignment1.ui.addstudent

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.ujjallamichhane.week6assignment1.HomeActivity
import com.ujjallamichhane.week6assignment1.R
import com.ujjallamichhane.week6assignment1.model.Student


class AddStudentFragment : Fragment() {

    private lateinit var dashboardViewModel: AddStudentViewModel

    private lateinit var etFullname: TextInputEditText
    private lateinit var etAge: TextInputEditText
    private lateinit var radioGroupGender: RadioGroup
    private lateinit var rdoMale: RadioButton
    private lateinit var rdoFemale: RadioButton
    private lateinit var rdoOthers: RadioButton
    private lateinit var etAddress: TextInputEditText
    private lateinit var etProfileLink: TextInputEditText
    private lateinit var btnSave: Button

    private var lstUser = arrayListOf<Student>()
    private var gender: String = ""

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_add_student, container, false)
        etFullname = root.findViewById(R.id.etFullname)
        etAge = root.findViewById(R.id.etAge)
        radioGroupGender = root.findViewById(R.id.radioGroupGender)
        rdoMale = root.findViewById(R.id.rdoMale)
        rdoFemale = root.findViewById(R.id.rdoFemale)
        rdoOthers = root.findViewById(R.id.rdoOthers)
        etAddress = root.findViewById(R.id.etAddress)
        etProfileLink = root.findViewById(R.id.etProfileLink)
        btnSave = root.findViewById(R.id.btnSave)

        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            lstUser = (activity as HomeActivity).lstUser
            radioGroupGender.setOnCheckedChangeListener { radioGroup, i ->
                when (i) {
                    R.id.rdoMale -> {
                        gender = "Male"
                    }
                    R.id.rdoFemale -> {
                        gender = "Female"
                    }
                    R.id.rdoOthers -> {
                        gender = "Others"
                    }
                }
            }

            btnSave.setOnClickListener {
                if (checkEmptyValues()) {
                    if (radioGroupGender.checkedRadioButtonId == -1) {
                        Toast.makeText(context, "Please select gender", Toast.LENGTH_SHORT).show()
                    } else {
                        val fullName = etFullname.text.toString()
                        val age = etAge.text.toString().toInt()
                        val gen = gender
                        val address = etAddress.text.toString()
                        val profileLink = etProfileLink.text.toString()

                        val user = Student(fullName, age, gen, address, profileLink)
                        lstUser.add(user)
                        Toast.makeText(context, "Student added", Toast.LENGTH_SHORT).show()

                        etFullname.setText("")
                        etAge.setText("")
                        radioGroupGender.clearCheck()
                        etAddress.setText("")
                        etProfileLink.setText("")
                    }
                }
            }
        })
        return root
    }

    private fun checkEmptyValues(): Boolean {
        var flag = true
        when {
            TextUtils.isEmpty(etFullname.text) -> {
                etFullname.error = "Please enter name"
                etFullname.requestFocus()
                flag = false
            }

            TextUtils.isEmpty(etAge.text) -> {
                etAge.error = "Please enter age"
                etAge.requestFocus()
                flag = false
            }

            TextUtils.isEmpty(etAddress.text) -> {
                etAddress.error = "Please enter address"
                etAddress.requestFocus()
                flag = false
            }

            TextUtils.isEmpty(etProfileLink.text) -> {
                etProfileLink.error = "Please enter profile image link"
                etProfileLink.requestFocus()
                flag = false
            }
        }
        return flag
    }
}