package de.animebook

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.facebook.imagepipeline.core.ImageTranscoderType
import com.facebook.imagepipeline.core.MemoryChunkType
import de.animebook.di.ApplicationModule
import de.animebook.feature.base.ApplicationComponentProvider
import org.rewedigital.katana.Component
import org.rewedigital.katana.Katana
import org.rewedigital.katana.android.environment.AndroidEnvironmentContext

@Suppress("unused")
class AnimeBookApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Fresco.initialize(this)
        Fresco.initialize(
            applicationContext,
            ImagePipelineConfig.newBuilder(applicationContext)
                .setMemoryChunkType(MemoryChunkType.BUFFER_MEMORY)
                .setImageTranscoderType(ImageTranscoderType.JAVA_TRANSCODER)
                .experiment().setNativeCodeDisabled(true)
                .build()
        )
        
        Katana.environmentContext =
            AndroidEnvironmentContext(profile = AndroidEnvironmentContext.Profile.MEMORY)
        ApplicationComponentProvider.applicationComponent =
            Component(ApplicationModule(this))
    }
}