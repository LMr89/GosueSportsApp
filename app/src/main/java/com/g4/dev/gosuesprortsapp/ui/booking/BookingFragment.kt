package com.g4.dev.gosuesprortsapp.ui.booking

import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.g4.dev.gosuesprortsapp.R
import com.g4.dev.gosuesprortsapp.data.model.request.booking.BookingRequest
import com.g4.dev.gosuesprortsapp.data.model.request.booking.IdOrdenador
import com.g4.dev.gosuesprortsapp.data.model.request.sale.IdCliente
import com.g4.dev.gosuesprortsapp.data.model.request.sale.IdUsuario
import com.g4.dev.gosuesprortsapp.data.model.response.common.CommonResponseServer
import com.g4.dev.gosuesprortsapp.databinding.FragmentBookingBinding
import com.g4.dev.gosuesprortsapp.util.alertsDialog.TimePickerDialogFragment
import com.g4.dev.gosuesprortsapp.util.messages.MessageType
import com.g4.dev.gosuesprortsapp.util.messages.MessageUtil
import java.text.SimpleDateFormat
import java.util.*

class BookingFragment : Fragment(), OnClickListener, AdapterView.OnItemSelectedListener {

    private var _binding: FragmentBookingBinding? = null
    lateinit var  viewModel: BookingViewModel

    lateinit var timePickerDialog: TimePickerDialog

    var selectedComputer = ""
    var dateHourChoose= 0
    var dateMinuteChoose=0


    var timeBookinHour = 0
    var timeBookingMinute = 0

    val PRICE_PER_HOUR = 1.5


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this).get(BookingViewModel::class.java)

        _binding = FragmentBookingBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
        initObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllComputers()
        initComponents()
    }
    private fun setUpListeners(){
        _binding?.btnChooseTime?.setOnClickListener(this)
        _binding!!.etDateTime.setOnClickListener(this)
        _binding!!.spComputers.onItemSelectedListener = this
        _binding!!.btnRegistrar.setOnClickListener(this)
    }
    override fun onClick(v: View) {
        when(v.id){
            R.id.btnChooseTime -> {
                showTimePickerDialog()
            }
            R.id.etDateTime -> {
                showTimePickerDialogDateTime()
            }
            R.id.btnRegistrar -> {
                sendRequest()
            }
        }
    }



    private fun showTimePickerDialogDateTime() {
        val timePicker = TimePickerDialogFragment{time ->
            onTimeSelectedForDateTime(time)
        }
        timePicker.show(requireActivity().supportFragmentManager, "time")
    }

    // TODO: wd
    private fun onTimeSelectedForDateTime(time: String) {
        val date = SimpleDateFormat("yyyy-MM-dd").format(Date())
        dateHourChoose = time.substring(0 , time.indexOf(":")).toInt()
        dateMinuteChoose = time.substring(time.indexOf(":")+1).toInt()
        Log.i("HORA SELECCIONADA" , "${date}T${time}")
        _binding!!.etDateTime.setText("${date}T${time}:00")

    }

    private fun showTimePickerDialog() {
        timePickerDialog.show()
    }

    private fun initObservers(){


        viewModel.computerList.observe(viewLifecycleOwner){
            with(_binding!!.spComputers){
                adapter = ArrayAdapter(requireContext().applicationContext, android.R.layout.simple_list_item_1, it)
            }
        }

        //viewModel.bookingResponse.observe(viewLifecycleOwner){
        //    receiveResponseFromServer(it)
        //}


    }


    private fun initComponents(){
        timePickerDialog = TimePickerDialog(activity as Context,{ view, hourOfDay, minute ->

            timeBookinHour = hourOfDay
            timeBookingMinute = minute
            onTimeSelected("${timeBookinHour}:${timeBookingMinute}")
        },
            1,10,true

        )
    }
    private fun onTimeSelected(time:String){
        _binding!!.etTimePicked.setText(time)

    }


    //Listeners for spinners
    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
        selectedComputer = parent.getItemAtPosition(position).toString().replace("COM-","").trim()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }


    private fun verifyInputs():Boolean{
        if (selectedComputer.isBlank()){
            MessageUtil.sendMessage(_binding!!.root,"Porfavor selecciona una computadora",MessageType.WARNING)
            return  false
        }
        if (_binding!!.etDateTime.text.toString().isBlank()){
            MessageUtil.sendMessage(_binding!!.root,"Porfavor selecciona la fecha de alquiler",MessageType.WARNING)
            return  false
        }
        if (_binding!!.etTimePicked.text.toString().isBlank()){
            MessageUtil.sendMessage(_binding!!.root,"Porfavor selecciona el tiempo de alquiler",MessageType.WARNING)
            return  false
        }

        if (!verifiyFecha() ){
            return  false
        }
        return  true
    }
    private fun sendRequest() {
        if (verifyInputs()){
            val bookingRequest = BookingRequest(
                _binding!!.etDateTime.text.toString(),
                getOnlyMinutesFromUserInput(),
                IdOrdenador(selectedComputer.toInt()),
                IdCliente(1),
                IdUsuario(4),
                (PRICE_PER_HOUR * timeBookinHour) + (kotlin.math.round(((PRICE_PER_HOUR *(timeBookingMinute / 100)) * 10)/10) )
            )
            viewModel.postNewBooking(bookingRequest)

        }

    }
    private fun verifiyFecha():Boolean{

        if (dateHourChoose < 9|| dateHourChoose > 21 ){

            MessageUtil.sendMessage(_binding!!.root,"Las reservas solo son de 9AM-9PM",MessageType.WARNING)
            return  false
        }


        val cal = Calendar.getInstance()
        val calendarUser = Calendar.getInstance()
        //Representa el objeto que da a las 10.pm
        val endCalendar = GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)
        ,21, 0,0)

         calendarUser.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH),
             dateHourChoose, dateMinuteChoose, 0)
        calendarUser.set(Calendar.MINUTE, getOnlyMinutesFromUserInput())


        if (calendarUser.after(endCalendar)){
            MessageUtil.sendMessage(_binding!!.root,"Los minutos superan la hora de cierre del local",MessageType.WARNING)
            return false
        }

        return true

    }
    private fun getOnlyMinutesFromUserInput():Int{
        return  (timeBookinHour*60) + timeBookingMinute

    }

    private fun receiveResponseFromServer(commonResponseServer: CommonResponseServer){
        if(commonResponseServer.httpStatus == 200){
            MessageUtil.sendMessage(_binding!!.root,"La reserva fue registrada satisfactoriamente",MessageType.SUCCESS)
            return
        }
        MessageUtil.sendMessage(_binding!!.root, commonResponseServer.mensaje,MessageType.ERROR)
    }

}