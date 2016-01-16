/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\Users\\jortegac\\Desktop\\POSA-15-master\\assignments\\assignment3\\src\\vandy\\mooc\\model\\aidl\\WeatherCall.aidl
 */
package vandy.mooc.model.aidl;
/**
 * Interface defining the method implemented within WeatherServiceSync
 * that provides synchronous access to the Weather Service web
 * service.
 */
public interface WeatherCall extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements vandy.mooc.model.aidl.WeatherCall
{
private static final java.lang.String DESCRIPTOR = "vandy.mooc.model.aidl.WeatherCall";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an vandy.mooc.model.aidl.WeatherCall interface,
 * generating a proxy if needed.
 */
public static vandy.mooc.model.aidl.WeatherCall asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof vandy.mooc.model.aidl.WeatherCall))) {
return ((vandy.mooc.model.aidl.WeatherCall)iin);
}
return new vandy.mooc.model.aidl.WeatherCall.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_getCurrentWeather:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.util.List<vandy.mooc.model.aidl.WeatherData> _result = this.getCurrentWeather(_arg0);
reply.writeNoException();
reply.writeTypedList(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements vandy.mooc.model.aidl.WeatherCall
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
/**
    * A two-way (blocking) call that retrieves information about the
    * current weather from the Weather Service web service and returns
    * a List of WeatherData objects containing the results from the
    * Weather Service web service back to the WeatherActivity.  If the
    * @a location isn't found then return a List with size 0.
    */
@Override public java.util.List<vandy.mooc.model.aidl.WeatherData> getCurrentWeather(java.lang.String location) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<vandy.mooc.model.aidl.WeatherData> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(location);
mRemote.transact(Stub.TRANSACTION_getCurrentWeather, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArrayList(vandy.mooc.model.aidl.WeatherData.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_getCurrentWeather = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
/**
    * A two-way (blocking) call that retrieves information about the
    * current weather from the Weather Service web service and returns
    * a List of WeatherData objects containing the results from the
    * Weather Service web service back to the WeatherActivity.  If the
    * @a location isn't found then return a List with size 0.
    */
public java.util.List<vandy.mooc.model.aidl.WeatherData> getCurrentWeather(java.lang.String location) throws android.os.RemoteException;
}
