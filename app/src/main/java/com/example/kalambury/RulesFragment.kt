package com.example.kalambury

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.text.HtmlCompat
import com.example.kalambury.helpers.NavigableFragment

class RulesFragment : NavigableFragment() {
    val htmlText = "<h2>What is Android?</h2>\n" + "<p>Android is an open source and Linux-based <b>Operating System</b> for mobile devices such as smartphones and tablet computers. Android was developed by the <i>Open Handset Alliance</i>, led by Google, and other companies.</p>\n" + "<p>Android offers a unified approach to application development for mobile devices which means developers need only develop for Android, and their applications should be able to run on different devices powered by Android.</p>\n" + "<p>The first beta version of the Android Software Development Kit (SDK) was released by Google in 2007 whereas the first commercial version, Android 1.0, was released in September 2008.</p>"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_rules, container, false)
        val rulesTextView = view.findViewById<TextView>(R.id.rulesTextView)

        rulesTextView.text = HtmlCompat.fromHtml(htmlText, 0);

        return view
    }
}
