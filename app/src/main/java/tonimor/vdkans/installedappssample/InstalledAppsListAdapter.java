package tonimor.vdkans.installedappssample;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class InstalledAppsListAdapter extends ArrayAdapter<ApplicationInfo> {

    private Context m_context = null;
    private PackageManager m_packageManager  = null;
    private List<ApplicationInfo> m_appsList = null;

    public InstalledAppsListAdapter(Context context, int textViewResourceId, List<ApplicationInfo> appsList) {
        super(context, textViewResourceId, appsList);
        this.m_context = context;
        this.m_appsList = appsList;
        m_packageManager = context.getPackageManager();
    }

    @Override
    public int getCount() {
        return ((null != m_appsList) ? m_appsList.size() : 0);
    }

    @Override
    public ApplicationInfo getItem(int position) {
        return ((null != m_appsList) ? m_appsList.get(position) : null);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (null == view) {
            LayoutInflater layoutInflater = (LayoutInflater)m_context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.elemlist_installed_app, null);
        }

        ApplicationInfo applicationInfo = m_appsList.get(position);
        if (null != applicationInfo) {
            TextView appName = (TextView) view.findViewById(R.id.app_name);
            TextView packageName = (TextView) view.findViewById(R.id.app_package);
            ImageView iconview = (ImageView) view.findViewById(R.id.app_icon);

            appName.setText(applicationInfo.loadLabel(m_packageManager));
            packageName.setText(applicationInfo.packageName);
            iconview.setImageDrawable(applicationInfo.loadIcon(m_packageManager));
        }
        return view;
    }
}
