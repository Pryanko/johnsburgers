package com.examle.libgo.johnsburgers.presentation.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.examle.libgo.johnsburgers.App;
import com.examle.libgo.johnsburgers.R;
import com.examle.libgo.johnsburgers.presentation.activities.contracts.BackPressedFragments;
import com.examle.libgo.johnsburgers.tools.BottomBarBadgeHelper;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.tinkoff.decoro.MaskImpl;
import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser;
import ru.tinkoff.decoro.slots.Slot;
import ru.tinkoff.decoro.watchers.FormatWatcher;
import ru.tinkoff.decoro.watchers.MaskFormatWatcher;

/**
 * @author libgo (03.12.2017)
 */

public class OrderFragment extends MvpAppCompatFragment implements BackPressedFragments {
        private BottomBarBadgeHelper bottomBarBadgeHelper = App.getAppComponent().getBottomBarBadgeHelper();

        @BindView(R.id.input_first_name_text)
        EditText inputFirstName;
        @BindView(R.id.input_telephone_name_text)
        EditText inputTelephone;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        if(bottomBarBadgeHelper.isBottomBarState()) {
            bottomBarBadgeHelper.setVisibleBottomBar(false);
        }


        ButterKnife.bind(this, view);
        Slot[] slots = new UnderscoreDigitSlotsParser().parseSlots("+7(___)___-__-__");
        FormatWatcher formatWatcher = new MaskFormatWatcher( // форматировать текст будет вот он
                MaskImpl.createTerminated(slots)
        );
        formatWatcher.installOn(inputTelephone); // устанавливаем форматтер на любой TextView

        return view;

    }

    @Override
    public void onBackPressed() {
        if(!bottomBarBadgeHelper.isBottomBarState()) {
            bottomBarBadgeHelper.setVisibleBottomBar(true);
        }
    }
}
