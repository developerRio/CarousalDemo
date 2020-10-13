package com.originalstocks.carousaldemo

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.Snackbar
import com.originalstocks.carousaldemo.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity(), ValueRetrieverInterface {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private var experienceList: List<TrendingModel>? = null
    private lateinit var carousalLayoutManager: CarousalLayoutManager
    private lateinit var hotelAdapter: TrendingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initExperienceData()

    }

    private fun initExperienceData() {
        experienceList = ArrayList<TrendingModel>()
        for (i in 0..5) {
            val trendingModel = TrendingModel(i)
            when (i) {
                0 -> {
                    trendingModel.backgroundImageLink =
                        "https://www.tajhotels.com/content/dam/luxury/hotels/Taj_Mahal_Mumbai/images/3x2/ViewoftheGatewayofIndia-3x2.jpg/_jcr_content/renditions/cq5dam.web.1280.1280.jpeg"
                    trendingModel.expTitle = "Hotel Taj"
                    trendingModel.locationText = "Mumbai, India"
                    trendingModel.profileImageLink =
                        "https://upload.wikimedia.org/wikipedia/en/thumb/c/cd/Taj_Hotels_logo.svg/1200px-Taj_Hotels_logo.svg.png"
                }
                1 -> {
                    trendingModel.backgroundImageLink =
                        "https://cf.bstatic.com/images/hotel/max1280x900/363/36388065.jpg"
                    trendingModel.expTitle = "Courtyard by Marriott"
                    trendingModel.locationText = "Bhopal, India"
                    trendingModel.profileImageLink =
                        "https://upload.wikimedia.org/wikipedia/en/thumb/c/cd/Taj_Hotels_logo.svg/1200px-Taj_Hotels_logo.svg.png"
                }
                2 -> {
                    trendingModel.backgroundImageLink =
                        "https://www.tajhotels.com/content/dam/luxury/hotels/taj-palace-delhi/images/at_a_glance/16x7/Facade3.jpg/jcr:content/renditions/cq5dam.web.1280.1280.jpeg"
                    trendingModel.expTitle = "Hotel Taj Mahal"
                    trendingModel.locationText = "New Delhi, India"
                    trendingModel.profileImageLink =
                        "https://upload.wikimedia.org/wikipedia/en/thumb/c/cd/Taj_Hotels_logo.svg/1200px-Taj_Hotels_logo.svg.png"
                }
                3 -> {
                    trendingModel.backgroundImageLink =
                        "https://miro.medium.com/max/2048/0*PNu-7hEGOIxZ9UjP.jpg"
                    trendingModel.expTitle = "Hotel Taj"
                    trendingModel.locationText = "Lucknow, India"
                    trendingModel.profileImageLink =
                        "https://upload.wikimedia.org/wikipedia/en/thumb/c/cd/Taj_Hotels_logo.svg/1200px-Taj_Hotels_logo.svg.png"
                }
                4 -> {
                    trendingModel.backgroundImageLink =
                        "https://img.etimg.com/thumb/width-1200,height-900,imgsize-259527,resizemode-1,msid-77288182/industry/services/hotels-/-restaurants/indian-hotel-to-start-home-delivery-of-food-in-kolkata.jpg"
                    trendingModel.expTitle = "Hotel Taj"
                    trendingModel.locationText = "Kolkata, India"
                    trendingModel.profileImageLink =
                        "https://upload.wikimedia.org/wikipedia/en/thumb/c/cd/Taj_Hotels_logo.svg/1200px-Taj_Hotels_logo.svg.png"
                }
                5 -> {
                    trendingModel.backgroundImageLink =
                        "https://www.oyster.com/uploads/sites/35/2019/05/pool-v16657538-1440-1024x683.jpg"
                    trendingModel.expTitle = "Hotel Taj"
                    trendingModel.locationText = "Udaipur, India"
                    trendingModel.profileImageLink =
                        "https://upload.wikimedia.org/wikipedia/en/thumb/c/cd/Taj_Hotels_logo.svg/1200px-Taj_Hotels_logo.svg.png"
                }
            }
            (experienceList as ArrayList<TrendingModel>).add(trendingModel)
        }
        binding.trendRecyclerView.setHasFixedSize(true)

        /** carousal */
        carousalLayoutManager = CarousalLayoutManager(this, RecyclerView.HORIZONTAL, false)

        binding.trendRecyclerView.layoutManager = carousalLayoutManager

        hotelAdapter = TrendingAdapter(
            this,
            experienceList as ArrayList<TrendingModel>,
            this
        )
        binding.trendRecyclerView.adapter = hotelAdapter

        /** This code was for reaching at the center of the list, which we don't need right now, as we have to reach to a specific card ! */

        /*binding.trendRecyclerView.post(Runnable {
            // Shifting the view to snap  near the center of the screen.
            // This does not have to be precise. From Google I/O-19, PagerSnapHelper can do the job by putting highlighted item into the accurate center.
            carousalLayoutManager.scrollToPosition((experienceList as ArrayList<TrendingModel>).size / 2)
            val dx: Int =
                (binding.trendRecyclerView.width - binding.trendRecyclerView.getChildAt(0).width) / 2
            binding.trendRecyclerView.scrollBy(-dx, 0)
            // Assign the LinearSnapHelper that will initially snap the near-center view.
            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(binding.trendRecyclerView)
        })*/

     }

    override fun onClickValueRetriever(position: Int, itemName: String) {
        Snackbar.make(binding.root, itemName, Snackbar.LENGTH_LONG).setTextColor(getColor(R.color.white)).show()
    }

    override fun onSpecificValueRetriever(position: Int, itemName: String) {
        Log.i(TAG, "onSpecificValueRetriever hotel name $itemName at position = $position")
        binding.trendRecyclerView.post(Runnable {
            // Shifting the view to snap  near the center of the screen.
            // This does not have to be precise. From Google I/O-19, PagerSnapHelper can do the job by putting highlighted item into the accurate center.
            carousalLayoutManager.scrollToPosition(position)
            // evaluating horizontal offset for better scrolling capabilities,
            val dx: Int =
                (binding.trendRecyclerView.width - binding.trendRecyclerView.getChildAt(position).width)
            Log.i(
                TAG,
                "onSpecificValueRetriever_dx = ${-dx} width = ${binding.trendRecyclerView.width} \n " +
                        "child position width = ${binding.trendRecyclerView.getChildAt(position).width}"
            )
            binding.trendRecyclerView.scrollBy(dx, 0)
            // Assigning the PagerSnapHelper that will initially snap the near-center view.
            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(binding.trendRecyclerView)
        })

    }


}


class TrendingAdapter(
    private val mContext: Context,
    trendingList: List<TrendingModel>,
    private val valueRetrieverInterface: ValueRetrieverInterface
) :
    RecyclerView.Adapter<TrendingAdapter.TrendingHolder>() {
    private val TAG = "TrendingAdapter"
    private val trendingList: List<TrendingModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingHolder {
        val view =
            LayoutInflater.from(mContext).inflate(R.layout.trending_news_feed_items, parent, false)
        return TrendingHolder(view)
    }

    override fun onBindViewHolder(holder: TrendingHolder, position: Int) {
        val trendingModel: TrendingModel = trendingList[position]

        // Setting Image via Glide
        Glide.with(mContext)
            .load(trendingModel.backgroundImageLink)
            .placeholder(R.drawable.default_placeholder)
            .into(holder.backgroundImageView)
        Glide.with(mContext)
            .load(trendingModel.profileImageLink)
            .placeholder(R.drawable.default_placeholder)
            .into(holder.profileImageView)
        holder.expTitleTextView.text = trendingModel.expTitle
        holder.locationTextView.text = trendingModel.locationText

        holder.hotelCardView.setOnClickListener {
            valueRetrieverInterface.onClickValueRetriever(
                position,
                (trendingModel.expTitle + " " + trendingModel.locationText)
            )
        }


        if (trendingModel.locationText!!.contains("Bhopal")) {
            Log.i(TAG, "initExperienceData: textToFindInList true at position $position")
            valueRetrieverInterface.onSpecificValueRetriever(
                position,
                (trendingModel.expTitle + " " + trendingModel.locationText)
            )
        } else {
            Log.e(TAG, "initExperienceData: textToFindInList false")
        }

    }

    override fun getItemCount(): Int {
        return trendingList.size
    }

    inner class TrendingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var expTitleTextView: TextView
        var locationTextView: TextView
        var backgroundImageView: ImageView
        var profileImageView: ImageView
        var hotelCardView: MaterialCardView

        init {
            hotelCardView = itemView.findViewById(R.id.experience_card_view)
            expTitleTextView = itemView.findViewById(R.id.exp_header_text_view)
            locationTextView = itemView.findViewById(R.id.location_exp_text_view)
            backgroundImageView = itemView.findViewById(R.id.exp_bg_img_view)
            profileImageView = itemView.findViewById(R.id.exp_profile_image_view)
        }
    }

    init {
        this.trendingList = trendingList
    }
}
