package com.cobranza.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cobranza.CobranzaApp;
import com.cobranza.R;
import com.cobranza.adapters.ContratoAdapter;
import com.cobranza.model.Articulo;
import com.cobranza.model.Contrato;
import com.cobranza.model.ContratoDao;
import com.cobranza.model.DaoSession;
import com.cobranza.model.Pago;
import com.cobranza.model.PagoDao;
import com.cobranza.model.Persona;
import com.cobranza.model.TipoPersona;

import org.greenrobot.greendao.query.Query;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zj.com.cn.bluetooth.sdk.BluetoothService;
import zj.com.cn.bluetooth.sdk.DeviceListActivity;
import zj.com.command.sdk.Command;
import zj.com.command.sdk.PrinterCommand;

public class MainActivity extends AppCompatActivity implements ContratoAdapter.ContratoClickListener
{
    /******************************************************************************************************/
    // Message types sent from the BluetoothService Handler
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_TOAST = 5;
    public static final int MESSAGE_CONNECTION_LOST = 6;
    public static final int MESSAGE_UNABLE_CONNECT = 7;
    /*******************************************************************************************************/
    // Key names received from the BluetoothService Handler
    public static final String DEVICE_NAME = "device_name";
    public static final String TOAST = "toast";
    // Debugging
    private static final String TAG = "MainActivity";
    private static final boolean DEBUG = true;
    // Intent request codes
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;
    private static final int REQUEST_CHOSE_BMP = 3;
    private static final int REQUEST_CAMER = 4;
    @BindView(R.id.recycler_view)
    RecyclerView mRvContratos;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.tv_lista_vacia)
    TextView mTvListaVacia;
    @BindView(R.id.button_scan)
    Button btnScan;
    /******************************************************************************************************/
    // Name of the connected device
    private String mConnectedDeviceName = null;
    /****************************************************************************************************/
    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what) {
                case MESSAGE_STATE_CHANGE:
                    if (DEBUG)
                        Log.i(TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);
                    switch (msg.arg1) {
                        case BluetoothService.STATE_CONNECTED:
                            //mTitle.setText(R.string.title_connected_to);
                            //mTitle.append(mConnectedDeviceName);
                            break;
                        case BluetoothService.STATE_CONNECTING:
                            //mTitle.setText(R.string.title_connecting);
                            break;
                        case BluetoothService.STATE_LISTEN:
                        case BluetoothService.STATE_NONE:
                            //mTitle.setText(R.string.title_not_connected);
                            break;
                    }
                    break;
                case MESSAGE_WRITE:

                    break;
                case MESSAGE_READ:

                    break;
                case MESSAGE_DEVICE_NAME:
                    // save the connected device's name
                    mConnectedDeviceName = msg.getData().getString(DEVICE_NAME);
                    Toast.makeText(getApplicationContext(),
                            "Connected to " + mConnectedDeviceName,
                            Toast.LENGTH_SHORT).show();
                    break;
                case MESSAGE_TOAST:
                    Toast.makeText(getApplicationContext(),
                            msg.getData().getString(TOAST), Toast.LENGTH_SHORT)
                            .show();
                    break;
                case MESSAGE_CONNECTION_LOST:
                    Toast.makeText(getApplicationContext(), "Device connection was lost",
                            Toast.LENGTH_SHORT).show();
                    break;
                case MESSAGE_UNABLE_CONNECT:
                    Toast.makeText(getApplicationContext(), "Unable to connect device",
                            Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    // Local Bluetooth adapter
    private BluetoothAdapter mBluetoothAdapter = null;
    // Member object for the services
    private BluetoothService mService = null;
    private Unbinder unbinder;
    private DaoSession daoSession;
    private ContratoDao contratoDao;
    private PagoDao pagoDao;
    private Query<Contrato> contratoQuery;
    private ContratoAdapter contratoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);

        mRvContratos.setHasFixedSize(true);
        mRvContratos.setLayoutManager(new LinearLayoutManager(this));

        contratoAdapter = new ContratoAdapter(this);
        mRvContratos.setAdapter(contratoAdapter);

        btnScan.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent serverIntent = new Intent(MainActivity.this, DeviceListActivity.class);
                startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
            }
        });

        CobranzaApp cobranzaApp = (CobranzaApp) getApplication();
        daoSession = cobranzaApp.getDaoSession();
        contratoDao = daoSession.getContratoDao();
        pagoDao = daoSession.getPagoDao();
        contratoQuery = contratoDao.queryBuilder().build();

        if (contratoDao.count() == 0) {
            fillDatabase();
        }


        actualizarLista();

        // Get local Bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // If the adapter is null, then Bluetooth is not supported
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not available",
                    Toast.LENGTH_LONG).show();
            finish();
        }

    }

    @Override
    public void onStart()
    {
        super.onStart();

        // If Bluetooth is not on, request that it be enabled.
        // setupChat() will then be called during onActivityResult
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(
                    BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
            // Otherwise, setup the session
        } else {
            if (mService == null)
                mService = new BluetoothService(this, mHandler);
        }
    }

    @Override
    public synchronized void onResume()
    {
        super.onResume();

        if (mService != null) {

            if (mService.getState() == BluetoothService.STATE_NONE) {
                // Start the Bluetooth services
                mService.start();
            }
        }
    }

    private void fillDatabase()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Calle 17 # 182\n");
        sb.append("Guadalupe Proletaria\n");
        sb.append("Gustavo A. Madero, 07670 CDMX \n");
        sb.append("entre av. 28 y av. Guadalupe");

        Persona cliente = new Persona();
        cliente.setId(1L);
        cliente.setTipoPersona(TipoPersona.CLIENTE);
        cliente.setEsAval(false);
        cliente.setNombre("Angel Gutiérrez Servin");
        cliente.setDireccion(sb.toString());

        sb = new StringBuilder();
        sb.append("Calle 17 # 207\n");
        sb.append("Guadalupe Proletaria\n");
        sb.append("Gustavo A. Madero, 07670 CDMX \n");
        sb.append("entre av. 28 y av. Santiago");

        Persona referencia = new Persona();
        referencia.setId(2L);
        referencia.setTipoPersona(TipoPersona.REFERENCIA);
        referencia.setEsAval(true);
        referencia.setNombre("Juan Servin");
        referencia.setDireccion(sb.toString());

        daoSession.getPersonaDao().insert(cliente);
        daoSession.getPersonaDao().insert(referencia);

        Contrato contrato = new Contrato();
        contrato.setId(1L);
        contrato.setNumero("0823728372");
        contrato.setDireccion(cliente.getDireccion());
        contrato.setCliente(cliente);
        contrato.setFecha(new Date());
        contrato.setMonto(10000.00);
        contrato.setSaldo(5000.00);
        contrato.setMontoLiquidar(3000.00);

        daoSession.getContratoDao().insert(contrato);

        Contrato c = daoSession.getContratoDao().load(1L);

        c.getReferencias().add(referencia);

        Articulo articulo = new Articulo();
        articulo.setId(1L);
        articulo.setContrato(c);
        articulo.setCantidad(1);
        articulo.setDescripcion("TV LG 32");
        articulo.setPrecioUnitario(8000.00);
        articulo.setPrecioVenta(10000.00);

        c.getArticulos().add(articulo);

        daoSession.getContratoDao().update(c);

        contrato = new Contrato();
        contrato.setId(2L);
        contrato.setNumero("9837378023");
        contrato.setDireccion(referencia.getDireccion());
        contrato.setCliente(referencia);
        contrato.setFecha(new Date());
        contrato.setMonto(15000.00);
        contrato.setSaldo(8000.00);
        contrato.setMontoLiquidar(5000.00);

        daoSession.getContratoDao().insert(contrato);

        c = daoSession.getContratoDao().load(2L);

        c.getReferencias().add(cliente);

        articulo = new Articulo();
        articulo.setId(2L);
        articulo.setContrato(c);
        articulo.setCantidad(1);
        articulo.setDescripcion("TV LG 60");
        articulo.setPrecioUnitario(10000.00);
        articulo.setPrecioVenta(15000.00);

        c.getArticulos().add(articulo);

        daoSession.getContratoDao().update(c);

    }

    private void actualizarLista()
    {
        List<Contrato> contratos = contratoQuery.list();
        progressBar.setVisibility(View.GONE);

        if (contratos.size() > 0) {
            mRvContratos.setVisibility(View.VISIBLE);
            mTvListaVacia.setVisibility(View.GONE);
            contratoAdapter.setContratos(contratos);
        } else {
            mRvContratos.setVisibility(View.GONE);
            mTvListaVacia.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        unbinder.unbind();

        if (mService != null)
            mService.stop();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (DEBUG)
            Log.d(TAG, "onActivityResult " + resultCode);
        switch (requestCode) {
            case REQUEST_CONNECT_DEVICE: {
                // When DeviceListActivity returns with a device to connect
                if (resultCode == Activity.RESULT_OK) {
                    // Get the device MAC address
                    String address = data.getExtras().getString(
                            DeviceListActivity.EXTRA_DEVICE_ADDRESS);
                    // Get the BLuetoothDevice object
                    if (BluetoothAdapter.checkBluetoothAddress(address)) {
                        BluetoothDevice device = mBluetoothAdapter
                                .getRemoteDevice(address);
                        // Attempt to connect to the device
                        mService.connect(device);
                    }
                }
                break;
            }
            case REQUEST_ENABLE_BT: {
                // When the request to enable Bluetooth returns
                if (resultCode == Activity.RESULT_OK) {
                    // Bluetooth is now enabled, so set up a session
                    mService = new BluetoothService(this, mHandler);
                } else {
                    // User did not enable Bluetooth or an error occured
                    Log.d(TAG, "BT not enabled");
                    Toast.makeText(this, R.string.bt_not_enabled_leaving,
                            Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            }
        }
    }

    @Override
    public void onContratoClick(int position)
    {
        final Long contratoId = contratoAdapter.getContrato(position).getId();

        LayoutInflater inflater = getLayoutInflater();

        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle(R.string.pago_titulo);
        alert.setMessage(R.string.pago_mensaje);
        View layout = inflater.inflate(R.layout.dialog_pago, null);
        alert.setView(layout);

        final EditText etMonto = (EditText) layout.findViewById(R.id.pago_monto);

        alert.setPositiveButton(R.string.pago_aceptar, new DialogInterface.OnClickListener()
        {

            @Override
            public void onClick(DialogInterface dialog, int i)
            {
                dialog.dismiss();

                Pago pago = new Pago();
                pago.setContratoId(contratoId);
                pago.setFecha(new Date());
                pago.setFechaPago(new Date());
                pago.setIdCobrador(100L);
                pago.setMonto(Double.parseDouble(etMonto.getText().toString()));
                Long id = pagoDao.insert(pago);

                imprimeRecibo(pago);

                Context context = getApplicationContext();
                CharSequence text = "El pago ha sido registrado";

                Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        alert.setNegativeButton(R.string.pago_cancelar, new DialogInterface.OnClickListener()
        {

            @Override
            public void onClick(DialogInterface dialog, int i)
            {
                dialog.dismiss();
            }
        });
        alert.show();
    }

    @Override
    public void onContratoLongClick(int position)
    {
        final Long contratoId = contratoAdapter.getContrato(position).getId();
        Intent detalleIntent = new Intent(getApplicationContext(), DetalleContratoActivity.class);
        detalleIntent.putExtra("contratoId", contratoId);
        startActivity(detalleIntent);
    }

    /*****************************************************************************************************/
    /*
	 * SendDataString
	 */
    private void SendDataString(String data)
    {

        if (mService.getState() != BluetoothService.STATE_CONNECTED) {
            Toast.makeText(this, R.string.not_connected, Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        if (data.length() > 0) {
            try {
                mService.write(data.getBytes("ISO-8859-1"));
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /*
     *SendDataByte
     */
    private void SendDataByte(byte[] data)
    {

        if (mService.getState() != BluetoothService.STATE_CONNECTED) {
            Toast.makeText(this, R.string.not_connected, Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        mService.write(data);
    }

    private void imprimeRecibo(Pago pago)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss ");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        String date = str + "\n\n\n\n\n\n";

        SendDataByte(Command.ESC_Align);
        Command.GS_ExclamationMark[2] = 0x11;
        SendDataByte(Command.GS_ExclamationMark);
        SendDataString("Muebleria Moderna\n");
        Command.ESC_Align[2] = 0x00;
        SendDataByte(Command.ESC_Align);
        Command.GS_ExclamationMark[2] = 0x00;
        SendDataByte(Command.GS_ExclamationMark);

        SendDataString(String.format("Se recibio de %s\nLa cantidad de $%(,.2f\npor concepto de abono a cuenta\ncon fecha %3$td/%3$tm/%3$tY\n",
                pago.getContrato().getCliente().getNombre(),
                pago.getMonto(),
                pago.getFechaPago()));

            /*SendDataByte("Number:  888888\nReceipt  S00003333\nCashier：1001\nDate：xxxx-xx-xx\nPrint Time：xxxx-xx-xx  xx:xx:xx\n".getBytes("UTF-8"));
            SendDataByte("Name    Quantity    price  Money\nShoes   10.00       899     8990\nBall    10.00       1599    15990\n".getBytes("UTF-8"));
            SendDataByte("Quantity：             20.00\ntotal：                16889.00\npayment：              17000.00\nKeep the change：      111.00\n".getBytes("UTF-8"));
            SendDataByte("company name：NIKE\nSite：www.xxx.xxx\naddress：ShenzhenxxAreaxxnumber\nphone number：0755-11111111\nHelpline：400-xxx-xxxx\n================================\n".getBytes("UTF-8"));
            */
        Command.ESC_Align[2] = 0x01;
        SendDataByte(Command.ESC_Align);
        Command.GS_ExclamationMark[2] = 0x11;
        SendDataByte(Command.GS_ExclamationMark);
        SendDataString("Gracias por su pago!\n");
        Command.ESC_Align[2] = 0x00;
        SendDataByte(Command.ESC_Align);
        Command.GS_ExclamationMark[2] = 0x00;
        SendDataByte(Command.GS_ExclamationMark);
        //SendDataByte("(The above information is for testing template, if agree, is purely coincidental!)\n".getBytes("UTF-8"));
        Command.ESC_Align[2] = 0x02;
        SendDataByte(Command.ESC_Align);
        SendDataString(date);
        SendDataByte(PrinterCommand.POS_Set_PrtAndFeedPaper(48));
        SendDataByte(Command.GS_V_m_n);

    }


}
