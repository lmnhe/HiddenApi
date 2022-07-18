package android.app;

import android.content.IIntentReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

import androidx.annotation.RequiresApi;

import java.util.List;

public interface IActivityManager extends IInterface {

    @RequiresApi(29)
    ContentProviderHolder getContentProviderExternal(String name, int userId, IBinder token, String tag)
            throws RemoteException;

    @RequiresApi(26)
    ContentProviderHolder getContentProviderExternal(String name, int userId, IBinder token)
            throws RemoteException;

    void removeContentProviderExternal(String name, IBinder token)
            throws RemoteException;

    int checkPermission(String permission, int pid, int uid)
            throws RemoteException;

    void registerProcessObserver(IProcessObserver observer)
            throws RemoteException;

    void unregisterProcessObserver(IProcessObserver observer)
            throws RemoteException;

    void registerUidObserver(IUidObserver observer, int which, int cutpoint, String callingPackage)
            throws RemoteException;

    void unregisterUidObserver(IUidObserver observer)
            throws RemoteException;

    void forceStopPackage(String packageName, int userId)
            throws RemoteException;

    int startActivityAsUser(IApplicationThread caller, String callingPackage,
                            Intent intent, String resolvedType, IBinder resultTo, String resultWho,
                            int requestCode, int flags, ProfilerInfo profilerInfo,
                            Bundle options, int userId)
            throws RemoteException;

    Intent registerReceiver(IApplicationThread caller, String callerPackage,
                            IIntentReceiver receiver, IntentFilter filter,
                            String requiredPermission, int userId)
            throws RemoteException;

    @RequiresApi(26)
    Intent registerReceiver(IApplicationThread caller, String callerPackage,
                            IIntentReceiver receiver, IntentFilter filter,
                            String requiredPermission, int userId, int flags)
            throws RemoteException;

    boolean isUserRunning(int userId, int flags)
            throws RemoteException;

    int broadcastIntent(IApplicationThread caller, Intent intent,
                        String resolvedType, IIntentReceiver resultTo, int resultCode,
                        String resultData, Bundle map, String[] requiredPermissions,
                        int appOp, Bundle options, boolean serialized, boolean sticky, int userId)
            throws RemoteException;

    @RequiresApi(26)
    int getUidProcessState(int uid, String callingPackage)
            throws RemoteException;

    int getPackageProcessState(String packageName, String callingPackage)
            throws RemoteException;

    /**
     * Method for the shell UID to start deletating its permission identity to an
     * active instrumenation. The shell can delegate permissions only to one active
     * instrumentation at a time. An active instrumentation is one running and
     * started from the shell.
     */
    @RequiresApi(Build.VERSION_CODES.Q)
    void startDelegateShellPermissionIdentity(int uid, String[] permissions);

    /**
     * Method for the shell UID to stop deletating its permission identity to an
     * active instrumenation. An active instrumentation is one running and
     * started from the shell.
     */
    @RequiresApi(Build.VERSION_CODES.Q)
    void stopDelegateShellPermissionIdentity();

    /**
     * Method for the shell UID to get currently adopted permissions for an active instrumentation.
     * An active instrumentation is one running and started from the shell.
     */
    @RequiresApi(Build.VERSION_CODES.Q)
    List<String> getDelegatedShellPermissions();

    @RequiresApi(26)
    abstract class Stub extends Binder implements IActivityManager {

        public static IActivityManager asInterface(IBinder obj) {
            throw new RuntimeException("STUB");
        }
    }
}
