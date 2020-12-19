package com.dirror.music.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dirror.music.MyApplication
import com.dirror.music.R
import com.dirror.music.databinding.ActivityLogin2Binding
import com.dirror.music.music.CloudMusic
import com.dirror.music.util.toast
import java.util.regex.Pattern

@Deprecated("将在 2.0 废弃")
class LoginActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityLogin2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogin2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
        initView()
        initListener()
    }

    private fun initData() {

    }

    private fun initView() {

    }

    private fun initListener() {
        binding.btnCancel.setOnClickListener {
            finish()
        }

        // 点击登录按钮
        binding.btnLoginByUid.setOnClickListener {
            // 获取输入
            var netease = binding.etUid.text.toString()

            // 判断是否直接是网易云分享用户链接
            if (netease != "") {
                val index = netease.indexOf("id=")
                if (index != -1) {
                    netease = netease.subSequence(index + 3, netease.length).toString()
                }
                netease = keepDigital(netease)
                // loge("数字：${netease}")
                if (netease != "") {
                    CloudMusic.loginByUid(netease) {
                        // 返回信息
                        val intent = Intent()
                        // intent.putExtra("boolean_user", true)
                        setResult(RESULT_OK, intent)
                        finish()
                    }
                } else {
                    toast("错误的 UID")
                }
            } else {
                toast("请输入 UID")
            }

        }

        binding.tvHelp.setOnClickListener {
            MyApplication.activityManager.startWebActivity(this, "https://moriafly.xyz/foyou/uidlogin.html")
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(
            R.anim.anim_no_anim,
            R.anim.anim_slide_exit_bottom
        )
    }

    /**
     * 只保留数字
     */
    private fun keepDigital(oldString: String): String {
        val newString = StringBuffer()
        val matcher = Pattern.compile("\\d").matcher(oldString)
        while (matcher.find()) {
            newString.append(matcher.group())
        }
        return newString.toString()
    }

}