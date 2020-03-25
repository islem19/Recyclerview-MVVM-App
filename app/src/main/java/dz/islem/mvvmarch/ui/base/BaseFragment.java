package dz.islem.mvvmarch.ui.base;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

public abstract class BaseFragment <VM extends ViewModel> extends Fragment {

    private VM viewModel;
    public abstract VM createViewModel();


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        viewModel = createViewModel();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
