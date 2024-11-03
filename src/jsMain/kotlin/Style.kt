package fr.xibalba.ajTextGameEngine

import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.theme.colors.palette.background
import org.jetbrains.compose.web.css.px
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.silk.init.registerStyleBase
import com.varabyte.kobweb.silk.style.breakpoint.BreakpointSizes
import com.varabyte.kobweb.silk.theme.colors.palette.button
import com.varabyte.kobweb.silk.theme.colors.palette.color
import fr.xibalba.ajTextGameEngine.utils.centered

@InitSilk
fun initializeBreakpoints(ctx: InitSilkContext) {
    ctx.theme.breakpoints = BreakpointSizes(
        sm = 640.px,
        md = 768.px,
        lg = 1024.px,
        xl = 1280.px
    )
}

@InitSilk
fun overrideTheme(ctx: InitSilkContext) {
    ctx.stylesheet.registerStyleBase("body") {
        Modifier.fontFamily("Ubuntu", "Roboto", "sans-serif")
            .fontSize(18.px)
    }

    ctx.theme.palettes.light.background = Color.rgb(0xF0F0F0)
    ctx.theme.palettes.light.color = Color.rgb(0x000000)
    ctx.theme.palettes.light.button.apply {
        default = ctx.theme.palettes.light.background.centered(0.1f)
        hover = default.centered(0.2f)
        focus = hover
        pressed = default.centered(0.25f)
    }

    ctx.theme.palettes.dark.background = Color.rgb(0x0D0D34)
    ctx.theme.palettes.dark.color = Color.rgb(0xFFFFFF)
    ctx.theme.palettes.dark.button.apply {
        default = ctx.theme.palettes.dark.background.centered(0.1f)
        hover = default.centered(0.2f)
        focus = hover
        pressed = default.centered(0.25f)
    }
}

