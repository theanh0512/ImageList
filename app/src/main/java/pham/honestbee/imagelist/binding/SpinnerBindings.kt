package pham.honestbee.imagelist.binding

import android.databinding.BindingAdapter
import android.databinding.InverseBindingAdapter
import android.databinding.InverseBindingListener
import android.widget.Spinner
import pham.honestbee.imagelist.extensions.SpinnerExtensions
import pham.honestbee.imagelist.extensions.SpinnerExtensions.getSpinnerValue
import pham.honestbee.imagelist.extensions.SpinnerExtensions.setSpinnerEntries
import pham.honestbee.imagelist.extensions.SpinnerExtensions.setSpinnerEntriesFromMap
import pham.honestbee.imagelist.extensions.SpinnerExtensions.setSpinnerInverseBindingListener
import pham.honestbee.imagelist.extensions.SpinnerExtensions.setSpinnerItemSelectedListener
import pham.honestbee.imagelist.extensions.SpinnerExtensions.setSpinnerValue

class SpinnerBindings {

    @BindingAdapter("stringEntries")
    fun Spinner.setStringEntries(entries: List<String>?) {
        setSpinnerEntries(entries)
    }

    @BindingAdapter("entries")
    fun Spinner.setEntries(entries: HashMap<String, Float>?) {
        setSpinnerEntriesFromMap(entries)
    }

    @BindingAdapter("onItemSelected")
    fun Spinner.setItemSelectedListener(itemSelectedListener: SpinnerExtensions.ItemSelectedListener?) {
        setSpinnerItemSelectedListener(itemSelectedListener)
    }

    @BindingAdapter("newValue")
    fun Spinner.setNewValue(newValue: Any?) {
        setSpinnerValue(newValue)
    }

    @BindingAdapter("selectedValue")
    fun Spinner.setSelectedValue(selectedValue: String?) {
        setSpinnerValue(selectedValue)
    }

    @BindingAdapter("selectedValueAttrChanged")
    fun Spinner.setInverseBindingListener(inverseBindingListener: InverseBindingListener?) {
        setSpinnerInverseBindingListener(inverseBindingListener)
    }

    companion object InverseSpinnerBindings {

        @JvmStatic
        @InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
        fun Spinner.getSelectedValue(): Any? {
            return getSpinnerValue()
        }
    }
}