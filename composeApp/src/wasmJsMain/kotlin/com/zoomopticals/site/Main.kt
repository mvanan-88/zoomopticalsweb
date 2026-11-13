package com.zoomopticals.site

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.ComposeViewport
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

private val Ink = Color(0xFF101215)
private val Muted = Color(0xFF62686C)
private val Line = Color(0xFFDCE1E3)
private val White = Color.White

@OptIn(ExperimentalComposeUiApi::class)
fun main() = ComposeViewport {
    MaterialTheme {
        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).background(White)) {
            PromoStrip()
            TopBar()
            CategoryNav()
            CampaignCarousel()
            Hero()
            Benefits()
            NewArrivals()
            BrandSpotlight()
            ShopByStyle()
            Favorites()
            Story()
            Heritage()
            ServicePromise()
            Footer()
        }
    }
}

@Composable
private fun CampaignCarousel() {
    val sl = listOf<DrawableResource>(Res.drawable.akoni_banner_anniversary_edition, Res.drawable.jacques_marie_mage_banner, Res.drawable.ray_ban_mata_ai_banner, Res.drawable.vava_eyewear_banner,Res.drawable.white_bg_desktop_jpg, Res.drawable.zeiss_web_banner)
    val slides: List<DrawableResource> = remember {
        sl
    }
    val selectedSlide = remember { mutableStateOf(0) }
    val slideCount = slides.size
    val selectedIndex = if (slideCount == 0) 0 else selectedSlide.value % slideCount

    Box(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.percent(), vertical = 12.dp)
            .height(530.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFEAE5DC))
    ) {
        if (slideCount > 0) {
            Image(
                painter = painterResource(slides[selectedIndex]),
                contentDescription = "ZoomOpticals campaign image ${selectedIndex + 1}",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            ModelArt(Modifier.fillMaxSize(), orangeFrames = false)
            Text(
                "Campaign images will appear here when added to drawable",
                Modifier.align(Alignment.BottomStart)
                    .padding(18.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xDDFFFFFF))
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                fontSize = 10.sp,
                color = Ink
            )
        }

        Text(
            "‹",
            Modifier.align(Alignment.CenterStart)
                .padding(start = 16.dp)
                .size(42.dp)
                .clip(CircleShape)
                .background(Color(0xEFFFFFFF))
                .clickable {
                    if (slideCount > 0) selectedSlide.value = (selectedIndex - 1 + slideCount) % slideCount
                },
            color = Ink,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            lineHeight = 40.sp
        )
        Text(
            "›",
            Modifier.align(Alignment.CenterEnd)
                .padding(end = 16.dp)
                .size(42.dp)
                .clip(CircleShape)
                .background(Color(0xEFFFFFFF))
                .clickable {
                    if (slideCount > 0) selectedSlide.value = (selectedIndex + 1) % slideCount
                },
            color = Ink,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            lineHeight = 40.sp
        )

        if (slideCount > 1) {
            Row(
                Modifier.align(Alignment.BottomCenter).padding(bottom = 15.dp)
                    .clip(CircleShape).background(Color(0xAAFFFFFF))
                    .padding(horizontal = 10.dp, vertical = 7.dp),
                horizontalArrangement = Arrangement.spacedBy(7.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                slides.indices.forEach { index ->
                    Box(
                        Modifier.size(if (index == selectedIndex) 9.dp else 7.dp)
                            .clip(CircleShape)
                            .background(if (index == selectedIndex) Ink else Color(0xFF9A9A96))
                            .clickable { selectedSlide.value = index }
                    )
                }
            }
        }
    }
}

@Composable
private fun TopBar() {
    Row(Modifier.fillMaxWidth().padding(horizontal = 6.percent(), vertical = 12.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(26.dp)) {
        Image(painterResource(Res.drawable.zoomopticals_logo), contentDescription = "ZoomOpticals logo", contentScale = ContentScale.Fit, modifier = Modifier.width(184.dp).height(74.dp))
        Row(Modifier.weight(1f).height(44.dp).clip(CircleShape).background(Color(0xFFF5F5F3)).border(1.dp, Line, CircleShape).clickable { }.padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(9.dp)) {
            Text("⌕", fontSize = 20.sp, color = Muted)
            Text("Search brands, models and styles", fontSize = 12.sp, color = Muted)
        }
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(18.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) { Text("♙", fontSize = 18.sp); Text("Account", fontSize = 9.sp, color = Muted) }
            Column(horizontalAlignment = Alignment.CenterHorizontally) { Text("♡", fontSize = 20.sp); Text("Wishlist", fontSize = 9.sp, color = Muted) }
            Column(horizontalAlignment = Alignment.CenterHorizontally) { Text("▱", fontSize = 18.sp); Text("Bag (0)", fontSize = 9.sp, color = Muted) }
        }
    }
}

@Composable
private fun PromoStrip() {
    Row(Modifier.fillMaxWidth().background(Ink).padding(horizontal = 18.dp, vertical = 9.dp), horizontalArrangement = Arrangement.Center) {
        Text("New season eyewear     ·     Frames for every point of view     ·     Personal fit guidance", fontSize = 10.sp, color = White, letterSpacing = .3.sp, textAlign = TextAlign.Center)
    }
}

@Composable
private fun CategoryNav() {
    Column {
        Row(Modifier.fillMaxWidth().padding(horizontal = 6.percent(), vertical = 14.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            listOf("New arrivals", "Sunglasses", "Eyeglasses", "Brands", "Lens & eye care", "Kids", "Accessories", "Our story").forEachIndexed { index, label ->
                Text(label.uppercase(), Modifier.clickable { }.padding(vertical = 4.dp), fontSize = 10.sp, color = if (index == 0) Color(0xFFB54232) else Ink, fontWeight = FontWeight.SemiBold, letterSpacing = .4.sp)
            }
        }
        Box(Modifier.fillMaxWidth().height(1.dp).background(Line))
    }
}

@Composable
private fun Hero() {
    Row(Modifier.fillMaxWidth().height(470.dp).background(Brush.verticalGradient(listOf(Color(0xFFF1EEE7), Color(0xFFFAF9F6), White))).padding(horizontal = 6.percent(), vertical = 32.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(18.dp)) {
        Column(Modifier.weight(1.05f), verticalArrangement = Arrangement.Center) {
            Text("A CLEARER\nPOINT OF VIEW", fontSize = 9.sp, letterSpacing = 1.7.sp, color = Muted)
            Spacer(Modifier.height(15.dp))
            Text("Eyewear,\nconsidered.", fontSize = 43.sp, lineHeight = 47.sp, fontWeight = FontWeight.Medium, letterSpacing = (-1.5).sp)
            Spacer(Modifier.height(13.dp))
            Text("Distinctive frames. Thoughtful optical care. Find the pair that feels like you.", fontSize = 13.sp, lineHeight = 20.sp, color = Muted)
            Spacer(Modifier.height(23.dp))
            DarkButton("Explore the collection   →")
            Spacer(Modifier.height(15.dp))
            Text("New season styles, selected for everyday life", fontSize = 10.sp, color = Muted)
        }
        Box(Modifier.weight(1.35f).fillMaxHeight().clip(RoundedCornerShape(18.dp)).background(Color(0xFFCFD3D1))) {
            ModelArt(Modifier.fillMaxSize(), orangeFrames = false)
            Text("THE NEW SEASON", Modifier.align(Alignment.TopEnd).padding(15.dp).clip(CircleShape).background(Color(0xCCFFFFFF)).padding(horizontal = 12.dp, vertical = 7.dp), fontSize = 9.sp, color = Ink)
            Row(Modifier.align(Alignment.BottomStart).padding(17.dp).clip(CircleShape).background(Color(0xCCFFFFFF)).padding(horizontal = 12.dp, vertical = 7.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                Text("ZOOM OPTICALS", fontSize = 9.sp, color = Ink, fontWeight = FontWeight.SemiBold)
                Text("A considered fit, every day", fontSize = 9.sp, color = Ink)
            }
        }
        Column(Modifier.weight(.7f), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Box(Modifier.fillMaxWidth().height(245.dp).clip(RoundedCornerShape(14.dp)).background(Color(0xFFE5E5E1))) {
                ProductStill(Modifier.fillMaxSize(), Color(0xFFB64B25), Color(0xFFD8C7AB))
                Text("HAND-FINISHED", Modifier.align(Alignment.BottomCenter).padding(bottom = 10.dp).clip(CircleShape).background(Color(0xCCFFFFFF)).padding(horizontal = 10.dp, vertical = 5.dp), fontSize = 8.sp)
            }
            Text("Small details. A distinctly personal point of view.", fontSize = 13.sp, lineHeight = 18.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
private fun Benefits() {
    Row(Modifier.fillMaxWidth().padding(horizontal = 6.percent(), vertical = 23.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        listOf("Considered frames" to "Styles for every day", "Expert guidance" to "Find your best fit", "Lens options" to "Clarity tailored to you", "Personal service" to "Here when you need us").forEachIndexed { index, item ->
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Box(Modifier.size(36.dp).clip(CircleShape).background(listOf(Color(0xFFEAF0F4), Color(0xFFF3F3DD), Color(0xFFDDEFF2), Color(0xFFF5E6D8))[index]), contentAlignment = Alignment.Center) { Text("✳", fontSize = 16.sp, color = Muted) }
                Column {
                    Text(item.first, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                    Text(item.second, fontSize = 9.sp, color = Muted)
                }
            }
        }
    }
}

@Composable
private fun BrandSpotlight() {
    Row(Modifier.fillMaxWidth().padding(horizontal = 6.percent(), vertical = 30.dp), horizontalArrangement = Arrangement.spacedBy(14.dp)) {
        listOf(
            Triple("THE ART OF EVERYDAY", "Frames that feel like you.", Color(0xFFE5DED1)),
            Triple("LIGHT, BY DESIGN", "Comfort in every detail.", Color(0xFFDDE4E0)),
            Triple("SEE WHAT'S NEXT", "New perspectives, just in.", Color(0xFFE9E2D9)),
        ).forEachIndexed { index, card ->
            Column(Modifier.weight(1f).height(168.dp).clip(RoundedCornerShape(12.dp)).background(card.third).clickable { }.padding(18.dp), verticalArrangement = Arrangement.SpaceBetween) {
                Text(card.first, fontSize = 9.sp, color = Muted, letterSpacing = 1.2.sp)
                Column {
                    Text(card.second, fontSize = 18.sp, lineHeight = 22.sp, fontWeight = FontWeight.Medium)
                    Spacer(Modifier.height(7.dp))
                    Text(if (index == 0) "Discover the collection   →" else "Explore now   →", fontSize = 10.sp, color = Ink)
                }
            }
        }
    }
}

@Composable
private fun ShopByStyle() {
    Column(Modifier.fillMaxWidth().padding(horizontal = 6.percent(), vertical = 22.dp)) {
        SectionHeading("Find your frame", "Shop by style")
        Spacer(Modifier.height(18.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            listOf("Aviator", "Round", "Cat-eye", "Rectangle", "Rimless", "Oversized").forEachIndexed { index, title ->
                Column(Modifier.weight(1f).clickable { }, horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(Modifier.fillMaxWidth().height(102.dp).clip(RoundedCornerShape(10.dp)).background(listOf(Color(0xFFF2F1ED), Color(0xFFEAE9E5), Color(0xFFEFECE7))[index % 3]), contentAlignment = Alignment.Center) {
                        Glasses(frames[index % frames.size], Modifier.fillMaxWidth(.78f).height(58.dp))
                    }
                    Spacer(Modifier.height(9.dp))
                    Text(title, fontSize = 11.sp, fontWeight = FontWeight.Medium)
                }
            }
        }
    }
}

@Composable
private fun SectionHeading(eyebrow: String, title: String) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom) {
        Column {
            Text(eyebrow.uppercase(), fontSize = 9.sp, color = Muted, letterSpacing = 1.4.sp)
            Spacer(Modifier.height(5.dp))
            Text(title, fontSize = 29.sp, letterSpacing = (-.6).sp, fontWeight = FontWeight.Medium)
        }
        Text("View all   →", Modifier.clickable { }, fontSize = 11.sp, color = Muted)
    }
}

@Composable
private fun Heritage() {
    Row(Modifier.fillMaxWidth().background(Color(0xFFF1F0EC)).padding(horizontal = 6.percent(), vertical = 48.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(42.dp)) {
        Column(Modifier.weight(1.05f)) {
            Text("A BETTER WAY TO SEE", fontSize = 9.sp, color = Muted, letterSpacing = 1.6.sp)
            Spacer(Modifier.height(10.dp))
            Text("Thoughtful care.\nA clearer point of view.", fontSize = 32.sp, lineHeight = 38.sp, fontWeight = FontWeight.Medium, letterSpacing = (-.7).sp)
            Spacer(Modifier.height(12.dp))
            Text("At ZoomOpticals, finding the right pair starts with listening. Explore considered frames and get personal guidance from people who care about how you see.", fontSize = 12.sp, lineHeight = 19.sp, color = Muted)
            Spacer(Modifier.height(18.dp))
            Text("Meet ZoomOpticals   →", Modifier.clip(CircleShape).background(Ink).clickable { }.padding(horizontal = 18.dp, vertical = 11.dp), fontSize = 10.sp, color = White)
        }
        Box(Modifier.weight(.95f).height(300.dp).clip(RoundedCornerShape(12.dp)).background(Color(0xFFCFCAC0))) {
            ModelArt(Modifier.fillMaxSize(), orangeFrames = false)
            Text("ZOOM OPTICALS  ·  MADE FOR YOU", Modifier.align(Alignment.BottomStart).padding(14.dp).clip(CircleShape).background(Color(0xDDFFFFFF)).padding(horizontal = 12.dp, vertical = 7.dp), fontSize = 8.sp, letterSpacing = .6.sp)
        }
    }
}

@Composable
private fun ServicePromise() {
    Row(Modifier.fillMaxWidth().padding(horizontal = 6.percent(), vertical = 32.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        listOf("Personal fit advice" to "Guidance from eyewear specialists", "Quality you can trust" to "Carefully selected frames and lenses", "Here when you need us" to "Support before and after your order").forEach { (title, caption) ->
            Column(Modifier.weight(1f).padding(horizontal = 10.dp)) {
                Text(title, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(5.dp))
                Text(caption, fontSize = 10.sp, color = Muted)
            }
        }
    }
}

private data class Frame(val name: String, val detail: String, val price: String, val tint: Color, val lens: Color)
private val frames = listOf(
    Frame("Nova", "Sleek acetate · Onyx", "₹8,500", Color(0xFF17191A), Color(0x99555D63)),
    Frame("Aster", "Warm tortoise · Amber", "₹9,200", Color(0xFF69462D), Color(0x88715C43)),
    Frame("Muse", "Soft square · Ink", "₹8,900", Color(0xFF1B1C20), Color(0x99535962)),
    Frame("Forma", "Lightweight metal · Black", "₹7,800", Color(0xFF202326), Color(0xAA464D52)),
    Frame("Arc", "Modern aviator · Graphite", "₹9,500", Color(0xFF22262B), Color(0xAA515B66)),
    Frame("Sol", "Round acetate · Indigo", "₹8,200", Color(0xFF30304C), Color(0xAA524F8D)),
)

@Composable
private fun Favorites() {
    Row(Modifier.fillMaxWidth().padding(horizontal = 6.percent(), vertical = 34.dp).height(370.dp), horizontalArrangement = Arrangement.spacedBy(24.dp), verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.weight(.92f).fillMaxHeight().clip(RoundedCornerShape(13.dp)).background(Color(0xFFB9BDB9))) { ModelArt(Modifier.fillMaxSize(), orangeFrames = false) }
        Column(Modifier.weight(1.95f).fillMaxHeight(), verticalArrangement = Arrangement.Center) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.SpaceBetween) {
                Spacer(Modifier.width(10.dp))
                Column(horizontalAlignment = Alignment.End) {
                    Text("Our Most", fontSize = 33.sp, lineHeight = 37.sp, textAlign = TextAlign.End)
                    Text("Loved Frames", fontSize = 33.sp, lineHeight = 37.sp, textAlign = TextAlign.End)
                    Spacer(Modifier.height(8.dp))
                    Text("Discover the styles our customers can't stop wearing.", fontSize = 10.sp, color = Muted)
                }
            }
            Spacer(Modifier.height(25.dp))
            Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                frames.take(3).forEach { FrameCard(it, Modifier.width(190.dp)) }
            }
        }
    }
}

@Composable
private fun NewArrivals() {
    Column(Modifier.fillMaxWidth().padding(horizontal = 6.percent(), vertical = 39.dp)) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom) {
            Column {
                Text("Fresh Drops", fontSize = 32.sp, letterSpacing = (-.8).sp)
                Spacer(Modifier.height(7.dp))
                Text("Explore the newest additions to our signature collection.", fontSize = 11.sp, color = Muted)
            }
            DarkButton("See All   ⊕")
        }
        Spacer(Modifier.height(23.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(14.dp)) {
            frames.takeLast(3).forEach { FrameCard(it, Modifier.weight(1f)) }
        }
    }
}

@Composable
private fun FrameCard(frame: Frame, modifier: Modifier = Modifier) {
    val liked = remember { mutableStateOf(false) }
    Column(modifier.clip(RoundedCornerShape(10.dp)).background(Color(0xFFF7F8F8)).border(1.dp, Line, RoundedCornerShape(10.dp)).padding(11.dp)) {
        Box(Modifier.fillMaxWidth().height(150.dp).clip(RoundedCornerShape(7.dp)).background(Color(0xFFF1F2F2))) {
            Text("ZOOM EDIT", Modifier.align(Alignment.TopStart).padding(8.dp).clip(RoundedCornerShape(4.dp)).background(Color(0xFFE3E4E4)).padding(horizontal = 7.dp, vertical = 4.dp), fontSize = 8.sp, letterSpacing = .3.sp)
            Text(if (liked.value) "♥" else "♡", Modifier.align(Alignment.TopEnd).padding(7.dp).clickable { liked.value = !liked.value }, fontSize = 16.sp)
            Row(Modifier.align(Alignment.CenterStart).padding(start = 9.dp, top = 30.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                listOf(Color(0xFF181A1B), Color(0xFFEC6F40), Color(0xFF9ACF56)).forEach { Box(Modifier.size(9.dp).background(it)) }
            }
            Glasses(frame, Modifier.align(Alignment.Center).fillMaxWidth(.8f).height(88.dp))
        }
        Spacer(Modifier.height(10.dp))
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Column {
                Text(frame.name, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
                Text(frame.detail, fontSize = 9.sp, color = Muted)
            }
            Text(frame.price, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
private fun Story() {
    Row(Modifier.fillMaxWidth().background(Ink).padding(horizontal = 6.percent(), vertical = 40.dp), horizontalArrangement = Arrangement.spacedBy(34.dp), verticalAlignment = Alignment.CenterVertically) {
        Column(Modifier.weight(1f)) {
            Text("THE ZOOM OPTICALS EDIT", fontSize = 9.sp, color = Color(0xFFD6C6AA), letterSpacing = 1.7.sp)
            Spacer(Modifier.height(12.dp))
            Text("A good frame\nchanges everything.", fontSize = 34.sp, lineHeight = 39.sp, letterSpacing = (-.8).sp, color = White, fontWeight = FontWeight.Medium)
            Spacer(Modifier.height(12.dp))
            Text("A collection made to bring your own point of view into focus.", fontSize = 12.sp, lineHeight = 18.sp, color = Color(0xFFD2D0CC))
            Spacer(Modifier.height(20.dp))
            Text("Discover our approach   →", Modifier.clip(CircleShape).background(White).clickable { }.padding(horizontal = 17.dp, vertical = 10.dp), fontSize = 10.sp, color = Ink)
        }
        Box(Modifier.weight(1f).height(280.dp).clip(RoundedCornerShape(12.dp))) { ModelArt(Modifier.fillMaxSize(), orangeFrames = true) }
    }
}

@Composable
private fun FeatureCard(title: String, body: String, icon: String, modifier: Modifier = Modifier) {
    Column(modifier.clip(RoundedCornerShape(10.dp)).background(Color(0xBFFFFFFF)).border(1.dp, Color(0x66FFFFFF), RoundedCornerShape(10.dp)).padding(15.dp)) {
        Text(icon, fontSize = 15.sp, color = Color(0xFF425B62))
        Spacer(Modifier.height(14.dp))
        Text(title, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(5.dp))
        Text(body, fontSize = 10.sp, lineHeight = 14.sp, color = Muted)
    }
}

@Composable
private fun ModelArt(modifier: Modifier, orangeFrames: Boolean) {
    Canvas(modifier) {
        val w = size.width; val h = size.height
        drawRect(Brush.verticalGradient(listOf(Color(0xFFB7E8F0), Color(0xFFE4E4DE), Color(0xFF8C8978))))
        drawCircle(Color(0xFF665344), w * .27f, androidx.compose.ui.geometry.Offset(w * .53f, h * .35f))
        drawOval(Color(0xFF6B533F), androidx.compose.ui.geometry.Offset(w * .27f, h * .06f), androidx.compose.ui.geometry.Size(w * .52f, h * .43f))
        drawOval(Color(0xFFD9B79B), androidx.compose.ui.geometry.Offset(w * .34f, h * .20f), androidx.compose.ui.geometry.Size(w * .39f, h * .37f))
        drawOval(Color(0xFF76543C), androidx.compose.ui.geometry.Offset(w * .31f, h * .16f), androidx.compose.ui.geometry.Size(w * .44f, h * .17f))
        drawRoundRect(Color(0xFFD9B79B), androidx.compose.ui.geometry.Offset(w * .44f, h * .47f), androidx.compose.ui.geometry.Size(w * .18f, h * .18f), androidx.compose.ui.geometry.CornerRadius(w * .05f))
        val frameColor = if (orangeFrames) Color(0xFFE76B35) else Color(0xFF262625)
        drawRoundRect(frameColor, androidx.compose.ui.geometry.Offset(w * .355f, h * .32f), androidx.compose.ui.geometry.Size(w * .17f, h * .095f), androidx.compose.ui.geometry.CornerRadius(w * .035f), style = Stroke(width = w * .012f))
        drawRoundRect(frameColor, androidx.compose.ui.geometry.Offset(w * .54f, h * .32f), androidx.compose.ui.geometry.Size(w * .17f, h * .095f), androidx.compose.ui.geometry.CornerRadius(w * .035f), style = Stroke(width = w * .012f))
        drawLine(frameColor, androidx.compose.ui.geometry.Offset(w * .525f, h * .36f), androidx.compose.ui.geometry.Offset(w * .54f, h * .36f), strokeWidth = w * .012f)
        drawRoundRect(Color(0xFF222D30), androidx.compose.ui.geometry.Offset(w * .11f, h * .63f), androidx.compose.ui.geometry.Size(w * .82f, h * .55f), androidx.compose.ui.geometry.CornerRadius(w * .2f))
        drawOval(Color(0xFFE8D3B2), androidx.compose.ui.geometry.Offset(w * .08f, h * .73f), androidx.compose.ui.geometry.Size(w * .82f, h * .66f))
        if (orangeFrames) {
            drawRoundRect(Color(0xFFF58A45), androidx.compose.ui.geometry.Offset(w * .34f, h * .31f), androidx.compose.ui.geometry.Size(w * .18f, h * .10f), androidx.compose.ui.geometry.CornerRadius(w * .035f), style = Stroke(width = w * .02f))
            drawRoundRect(Color(0xFFF58A45), androidx.compose.ui.geometry.Offset(w * .54f, h * .31f), androidx.compose.ui.geometry.Size(w * .18f, h * .10f), androidx.compose.ui.geometry.CornerRadius(w * .035f), style = Stroke(width = w * .02f))
        }
    }
}

@Composable
private fun ProductStill(modifier: Modifier, frameColor: Color, background: Color) {
    Canvas(modifier) {
        drawRect(Brush.verticalGradient(listOf(background, Color(0xFFE9E7E0))))
        drawOval(Color(0x55FFFFFF), androidx.compose.ui.geometry.Offset(size.width * .07f, size.height * .12f), androidx.compose.ui.geometry.Size(size.width * .76f, size.height * .72f))
        val lensW = size.width * .29f; val lensH = size.height * .23f; val y = size.height * .35f
        drawRoundRect(frameColor, androidx.compose.ui.geometry.Offset(size.width * .17f, y), androidx.compose.ui.geometry.Size(lensW, lensH), androidx.compose.ui.geometry.CornerRadius(18f), style = Stroke(width = size.width * .025f))
        drawRoundRect(frameColor, androidx.compose.ui.geometry.Offset(size.width * .53f, y), androidx.compose.ui.geometry.Size(lensW, lensH), androidx.compose.ui.geometry.CornerRadius(18f), style = Stroke(width = size.width * .025f))
        drawLine(frameColor, androidx.compose.ui.geometry.Offset(size.width * .46f, y + lensH * .25f), androidx.compose.ui.geometry.Offset(size.width * .53f, y + lensH * .25f), strokeWidth = size.width * .025f)
        drawLine(frameColor, androidx.compose.ui.geometry.Offset(size.width * .17f, y + lensH * .2f), androidx.compose.ui.geometry.Offset(size.width * .02f, y - lensH * .25f), strokeWidth = size.width * .018f)
        drawLine(frameColor, androidx.compose.ui.geometry.Offset(size.width * .82f, y + lensH * .2f), androidx.compose.ui.geometry.Offset(size.width * .98f, y - lensH * .25f), strokeWidth = size.width * .018f)
    }
}

@Composable
private fun Glasses(frame: Frame, modifier: Modifier = Modifier) {
    Canvas(modifier) {
        val stroke = Stroke(width = 5.dp.toPx(), cap = StrokeCap.Round)
        val y = size.height * .28f; val lensW = size.width * .39f; val lensH = size.height * .48f
        drawRoundRect(frame.lens, androidx.compose.ui.geometry.Offset(0f, y), androidx.compose.ui.geometry.Size(lensW, lensH), androidx.compose.ui.geometry.CornerRadius(18f))
        drawRoundRect(frame.tint, androidx.compose.ui.geometry.Offset(0f, y), androidx.compose.ui.geometry.Size(lensW, lensH), androidx.compose.ui.geometry.CornerRadius(18f), style = stroke)
        drawRoundRect(frame.lens, androidx.compose.ui.geometry.Offset(size.width - lensW, y), androidx.compose.ui.geometry.Size(lensW, lensH), androidx.compose.ui.geometry.CornerRadius(18f))
        drawRoundRect(frame.tint, androidx.compose.ui.geometry.Offset(size.width - lensW, y), androidx.compose.ui.geometry.Size(lensW, lensH), androidx.compose.ui.geometry.CornerRadius(18f), style = stroke)
        val bridge = Path().apply { moveTo(lensW, y + lensH * .3f); quadraticTo(size.width / 2, y + lensH * .05f, size.width - lensW, y + lensH * .3f) }
        drawPath(bridge, frame.tint, style = stroke)
        drawLine(frame.tint, androidx.compose.ui.geometry.Offset(0f, y + 8), androidx.compose.ui.geometry.Offset(-size.width * .12f, y - 5), strokeWidth = 4.dp.toPx(), cap = StrokeCap.Round)
        drawLine(frame.tint, androidx.compose.ui.geometry.Offset(size.width, y + 8), androidx.compose.ui.geometry.Offset(size.width * 1.12f, y - 5), strokeWidth = 4.dp.toPx(), cap = StrokeCap.Round)
    }
}

@Composable
private fun Footer() {
    Column(Modifier.fillMaxWidth().background(Color(0xFFF4F3F0)).padding(horizontal = 6.percent(), vertical = 30.dp)) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.SpaceBetween) {
            Column(Modifier.weight(1.25f)) {
                Image(painterResource(Res.drawable.zoomopticals_logo), contentDescription = "ZoomOpticals logo", contentScale = ContentScale.Fit, modifier = Modifier.width(170.dp).height(70.dp))
                Spacer(Modifier.height(8.dp))
                Text("Eyewear, considered.", fontSize = 11.sp, color = Muted)
            }
            FooterColumn("Explore", listOf("New arrivals", "Sunglasses", "Eyeglasses", "Shop all frames"), Modifier.weight(1f))
            FooterColumn("ZoomOpticals", listOf("Our story", "Lens & eye care", "Find your fit", "Contact us"), Modifier.weight(1f))
            FooterColumn("Customer care", listOf("Shipping & returns", "FAQs", "Privacy", "Terms"), Modifier.weight(1f))
        }
        Spacer(Modifier.height(26.dp))
        Box(Modifier.fillMaxWidth().height(1.dp).background(Line))
        Spacer(Modifier.height(14.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("© 2026 ZoomOpticals. All rights reserved.", fontSize = 9.sp, color = Muted)
            Text("Made for the way you see.", fontSize = 9.sp, color = Muted)
        }
    }
}

@Composable
private fun FooterColumn(title: String, links: List<String>, modifier: Modifier = Modifier) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(9.dp)) {
        Text(title, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
        links.forEach { Text(it, Modifier.clickable { }, fontSize = 10.sp, color = Muted) }
    }
}

@Composable
private fun DarkButton(label: String) {
    Text(label, Modifier.clip(CircleShape).background(Color.Black).clickable { }.padding(horizontal = 19.dp, vertical = 12.dp), fontSize = 11.sp, color = White, fontWeight = FontWeight.SemiBold)
}

private fun Int.percent(): androidx.compose.ui.unit.Dp = (this * 5).dp
