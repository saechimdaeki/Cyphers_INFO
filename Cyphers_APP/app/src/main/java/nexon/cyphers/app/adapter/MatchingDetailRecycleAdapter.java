package nexon.cyphers.app.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import nexon.cyphers.app.Matching_result;
import nexon.cyphers.app.R;
import nexon.cyphers.app.databinding.MatchingAllResultDetailBinding;
import nexon.cyphers.app.model.RecyclerViewModel.MatchingResultDetailModel;
import nexon.cyphers.app.model.item.itemModel;
import nexon.cyphers.app.retrofit2.RetrofitFactory;
import nexon.cyphers.app.retrofit2.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchingDetailRecycleAdapter extends RecyclerView.Adapter<MatchingDetailRecycleAdapter.MatchItemViewHolder> {


    private ArrayList<MatchingResultDetailModel> listData = new ArrayList<>();
    MatchingAllResultDetailBinding binding;
    @NonNull
    @Override
    public MatchItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.matching_all_result_detail, parent, false);
        return new MatchItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MatchItemViewHolder holder, final int position) {
        holder.onBind(listData.get(position));
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context=view.getContext();
                Intent intent=new Intent(context, Matching_result.class);
                intent.putExtra("nick",holder.nickname.getText());
                context.startActivity(intent);
            }
        });
        /* 노가다 */
        holder.item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context=view.getContext();
                RetrofitService networkService= RetrofitFactory.create();
                networkService.GetItemDetail(String.valueOf(holder.itemid1.getText()),holder.itemView.getContext().getString(R.string.API_KEY))
                        .enqueue(new Callback<itemModel>() {
                            @Override
                            public void onResponse(Call<itemModel> call, Response<itemModel> response) {
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                                if (response.body() != null) {
                                    alertDialog.setTitle(response.body().getItemName());
                                    alertDialog.setMessage(response.body().getExplainDetail());
                                }
                                alertDialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                AlertDialog dialog = alertDialog.create();
                                dialog.show();
                            }
                            @Override
                            public void onFailure(Call<itemModel> call, Throwable t) {
                            }
                        });
            }
        });
        holder.item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context=view.getContext();
                RetrofitService networkService= RetrofitFactory.create();
                networkService.GetItemDetail(String.valueOf(holder.itemid2.getText()),holder.itemView.getContext().getString(R.string.API_KEY))
                        .enqueue(new Callback<itemModel>() {
                            @Override
                            public void onResponse(Call<itemModel> call, Response<itemModel> response) {
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                                if (response.body() != null) {
                                    alertDialog.setTitle(response.body().getItemName());
                                    alertDialog.setMessage(response.body().getExplainDetail());
                                }

                                alertDialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                AlertDialog dialog = alertDialog.create();
                                dialog.show();
                            }
                            @Override
                            public void onFailure(Call<itemModel> call, Throwable t) {
                            }
                        });
            }
        });
        holder.item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context=view.getContext();
                RetrofitService networkService= RetrofitFactory.create();
                networkService.GetItemDetail(String.valueOf(holder.itemid3.getText()),holder.itemView.getContext().getString(R.string.API_KEY))
                        .enqueue(new Callback<itemModel>() {
                            @Override
                            public void onResponse(Call<itemModel> call, Response<itemModel> response) {
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                                if (response.body() != null) {
                                    alertDialog.setTitle(response.body().getItemName());
                                    alertDialog.setMessage(response.body().getExplainDetail());
                                }

                                alertDialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                AlertDialog dialog = alertDialog.create();
                                dialog.show();
                            }
                            @Override
                            public void onFailure(Call<itemModel> call, Throwable t) {
                            }
                        });
            }
        });
        holder.item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context=view.getContext();
                RetrofitService networkService= RetrofitFactory.create();
                networkService.GetItemDetail(String.valueOf(holder.itemid4.getText()),holder.itemView.getContext().getString(R.string.API_KEY))
                        .enqueue(new Callback<itemModel>() {
                            @Override
                            public void onResponse(Call<itemModel> call, Response<itemModel> response) {
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                                if (response.body() != null) {
                                    alertDialog.setTitle(response.body().getItemName());
                                    alertDialog.setMessage(response.body().getExplainDetail());
                                }

                                alertDialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                AlertDialog dialog = alertDialog.create();
                                dialog.show();
                            }
                            @Override
                            public void onFailure(Call<itemModel> call, Throwable t) {
                            }
                        });
            }
        });
        holder.item5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context=view.getContext();
                RetrofitService networkService= RetrofitFactory.create();
                networkService.GetItemDetail(String.valueOf(holder.itemid5.getText()),holder.itemView.getContext().getString(R.string.API_KEY))
                        .enqueue(new Callback<itemModel>() {
                            @Override
                            public void onResponse(Call<itemModel> call, Response<itemModel> response) {
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                                if (response.body() != null) {
                                    alertDialog.setTitle(response.body().getItemName());
                                    alertDialog.setMessage(response.body().getExplainDetail());
                                }

                                alertDialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                AlertDialog dialog = alertDialog.create();
                                dialog.show();
                            }
                            @Override
                            public void onFailure(Call<itemModel> call, Throwable t) {
                            }
                        });
            }
        });
        holder.item6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context=view.getContext();
                RetrofitService networkService= RetrofitFactory.create();
                networkService.GetItemDetail(String.valueOf(holder.itemid6.getText()),holder.itemView.getContext().getString(R.string.API_KEY))
                        .enqueue(new Callback<itemModel>() {
                            @Override
                            public void onResponse(Call<itemModel> call, Response<itemModel> response) {
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                                if (response.body() != null) {
                                    alertDialog.setTitle(response.body().getItemName());
                                    alertDialog.setMessage(response.body().getExplainDetail());
                                }

                                alertDialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                AlertDialog dialog = alertDialog.create();
                                dialog.show();
                            }
                            @Override
                            public void onFailure(Call<itemModel> call, Throwable t) {
                            }
                        });
            }
        });
        holder.item7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context=view.getContext();
                RetrofitService networkService= RetrofitFactory.create();
                networkService.GetItemDetail(String.valueOf(holder.itemid7.getText()),holder.itemView.getContext().getString(R.string.API_KEY))
                        .enqueue(new Callback<itemModel>() {
                            @Override
                            public void onResponse(Call<itemModel> call, Response<itemModel> response) {
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                                if (response.body() != null) {
                                    alertDialog.setTitle(response.body().getItemName());
                                    alertDialog.setMessage(response.body().getExplainDetail());
                                }

                                alertDialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                AlertDialog dialog = alertDialog.create();
                                dialog.show();
                            }
                            @Override
                            public void onFailure(Call<itemModel> call, Throwable t) {
                            }
                        });
            }
        });
        holder.item8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context=view.getContext();
                RetrofitService networkService= RetrofitFactory.create();
                networkService.GetItemDetail(String.valueOf(holder.itemid8.getText()),holder.itemView.getContext().getString(R.string.API_KEY))
                        .enqueue(new Callback<itemModel>() {
                            @Override
                            public void onResponse(Call<itemModel> call, Response<itemModel> response) {
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                                if (response.body() != null) {
                                    alertDialog.setTitle(response.body().getItemName());
                                    alertDialog.setMessage(response.body().getExplainDetail());
                                }

                                alertDialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                AlertDialog dialog = alertDialog.create();
                                dialog.show();
                            }
                            @Override
                            public void onFailure(Call<itemModel> call, Throwable t) {
                            }
                        });
            }
        });
        holder.item9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context=view.getContext();
                RetrofitService networkService= RetrofitFactory.create();
                networkService.GetItemDetail(String.valueOf(holder.itemid9.getText()),holder.itemView.getContext().getString(R.string.API_KEY))
                        .enqueue(new Callback<itemModel>() {
                            @Override
                            public void onResponse(Call<itemModel> call, Response<itemModel> response) {
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                                if (response.body() != null) {
                                    alertDialog.setTitle(response.body().getItemName());
                                    alertDialog.setMessage(response.body().getExplainDetail());
                                }

                                alertDialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                AlertDialog dialog = alertDialog.create();
                                dialog.show();
                            }
                            @Override
                            public void onFailure(Call<itemModel> call, Throwable t) {
                            }
                        });
            }
        });
        holder.item10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context=view.getContext();
                RetrofitService networkService= RetrofitFactory.create();
                networkService.GetItemDetail(String.valueOf(holder.itemid10.getText()),holder.itemView.getContext().getString(R.string.API_KEY))
                        .enqueue(new Callback<itemModel>() {
                            @Override
                            public void onResponse(Call<itemModel> call, Response<itemModel> response) {
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                                if (response.body() != null) {
                                    alertDialog.setTitle(response.body().getItemName());
                                    alertDialog.setMessage(response.body().getExplainDetail());
                                }

                                alertDialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                AlertDialog dialog = alertDialog.create();
                                dialog.show();
                            }
                            @Override
                            public void onFailure(Call<itemModel> call, Throwable t) {
                            }
                        });
            }
        });
        holder.item11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context=view.getContext();
                RetrofitService networkService= RetrofitFactory.create();
                networkService.GetItemDetail(String.valueOf(holder.itemid11.getText()),holder.itemView.getContext().getString(R.string.API_KEY))
                        .enqueue(new Callback<itemModel>() {
                            @Override
                            public void onResponse(Call<itemModel> call, Response<itemModel> response) {
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                                if (response.body() != null) {
                                    alertDialog.setTitle(response.body().getItemName());
                                    alertDialog.setMessage(response.body().getExplainDetail());
                                }

                                alertDialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                AlertDialog dialog = alertDialog.create();
                                dialog.show();
                            }
                            @Override
                            public void onFailure(Call<itemModel> call, Throwable t) {
                            }
                        });
            }
        });
        holder.item12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context=view.getContext();
                RetrofitService networkService= RetrofitFactory.create();
                networkService.GetItemDetail(String.valueOf(holder.itemid12.getText()),holder.itemView.getContext().getString(R.string.API_KEY))
                        .enqueue(new Callback<itemModel>() {
                            @Override
                            public void onResponse(Call<itemModel> call, Response<itemModel> response) {
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                                if (response.body() != null) {
                                    alertDialog.setTitle(response.body().getItemName());
                                    alertDialog.setMessage(response.body().getExplainDetail());
                                }

                                alertDialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                AlertDialog dialog = alertDialog.create();
                                dialog.show();
                            }
                            @Override
                            public void onFailure(Call<itemModel> call, Throwable t) {
                            }
                        });
            }
        });
        holder.item13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context=view.getContext();
                RetrofitService networkService= RetrofitFactory.create();
                networkService.GetItemDetail(String.valueOf(holder.itemid13.getText()),holder.itemView.getContext().getString(R.string.API_KEY))
                        .enqueue(new Callback<itemModel>() {
                            @Override
                            public void onResponse(Call<itemModel> call, Response<itemModel> response) {
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                                if (response.body() != null) {
                                    alertDialog.setTitle(response.body().getItemName());
                                    alertDialog.setMessage(response.body().getExplainDetail());
                                }
                                alertDialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                AlertDialog dialog = alertDialog.create();
                                dialog.show();
                            }
                            @Override
                            public void onFailure(Call<itemModel> call, Throwable t) {
                            }
                        });
            }
        });
        holder.item14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context=view.getContext();
                RetrofitService networkService= RetrofitFactory.create();
                networkService.GetItemDetail(String.valueOf(holder.itemid14.getText()),holder.itemView.getContext().getString(R.string.API_KEY))
                        .enqueue(new Callback<itemModel>() {
                            @Override
                            public void onResponse(Call<itemModel> call, Response<itemModel> response) {
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                                if (response.body() != null) {
                                    alertDialog.setTitle(response.body().getItemName());
                                    alertDialog.setMessage(response.body().getExplainDetail());
                                }

                                alertDialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                AlertDialog dialog = alertDialog.create();
                                dialog.show();
                            }
                            @Override
                            public void onFailure(Call<itemModel> call, Throwable t) {
                            }
                        });
            }
        });
        holder.item15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context=view.getContext();
                RetrofitService networkService= RetrofitFactory.create();
                networkService.GetItemDetail(String.valueOf(holder.itemid15.getText()),holder.itemView.getContext().getString(R.string.API_KEY))
                        .enqueue(new Callback<itemModel>() {
                            @Override
                            public void onResponse(Call<itemModel> call, Response<itemModel> response) {
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                                if (response.body() != null) {
                                    alertDialog.setTitle(response.body().getItemName());
                                    alertDialog.setMessage(response.body().getExplainDetail());
                                }

                                alertDialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                AlertDialog dialog = alertDialog.create();
                                dialog.show();
                            }
                            @Override
                            public void onFailure(Call<itemModel> call, Throwable t) {
                            }
                        });
            }
        });
        holder.item16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context=view.getContext();
                RetrofitService networkService= RetrofitFactory.create();
                networkService.GetItemDetail(String.valueOf(holder.itemid16.getText()),holder.itemView.getContext().getString(R.string.API_KEY))
                        .enqueue(new Callback<itemModel>() {
                            @Override
                            public void onResponse(Call<itemModel> call, Response<itemModel> response) {
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                                if (response.body() != null) {
                                    alertDialog.setTitle(response.body().getItemName());
                                    alertDialog.setMessage(response.body().getExplainDetail());
                                }

                                alertDialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                AlertDialog dialog = alertDialog.create();
                                dialog.show();
                            }
                            @Override
                            public void onFailure(Call<itemModel> call, Throwable t) {
                            }
                        });
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
    public void addItem(MatchingResultDetailModel data) {
        listData.add(data);
    }
    class MatchItemViewHolder extends RecyclerView.ViewHolder {
        private TextView TeamResult;
        private LinearLayout linearLayout;
        private TextView TAG;
        private TextView dealPoint;
        private TextView damagedPoint;
        private TextView characterNameLevel;
        private TextView killDeathAssist;
        private TextView kda;
        private TextView battlePoint;
        private TextView sightPoint;
        private CircleImageView characterImage;
        private CircleImageView characterPosition;
        private CircleImageView Attribute1;
        private CircleImageView Attribute2;
        private CircleImageView Attribute3;
        private CircleImageView item1;
        private CircleImageView item2;
        private CircleImageView item3;
        private CircleImageView item4;
        private CircleImageView item5;
        private CircleImageView item6;
        private CircleImageView item7;
        private CircleImageView item8;
        private CircleImageView item9;
        private CircleImageView item10;
        private CircleImageView item11;
        private CircleImageView item12;
        private CircleImageView item13;
        private CircleImageView item14;
        private CircleImageView item15;
        private CircleImageView item16;
        private TextView nickname;
        private TextView eachPlayerID;
        private TextView itemid1,itemid2,itemid3,itemid4,itemid5,itemid6,itemid7,itemid8,itemid9,itemid10,itemid11,itemid12,itemid13,itemid14,itemid15,itemid16;
        private View mView;
        MatchItemViewHolder(View itemView) {
            super(itemView);
            linearLayout=itemView.findViewById(R.id.detail_recycle);
            TAG=itemView.findViewById(R.id.matching_Detail_TAG);
            dealPoint=itemView.findViewById(R.id.player_detail_deal_point);
            damagedPoint=itemView.findViewById(R.id.player_detail_damaged_point);
            characterNameLevel=itemView.findViewById(R.id.matching_character_detail_NameAndLevel);
            killDeathAssist=itemView.findViewById(R.id.player_detail_killdeathassist);
            kda=itemView.findViewById(R.id.player_detail_killdeathassistresult);
            battlePoint=itemView.findViewById(R.id.player_detail_battlepoint);
            sightPoint=itemView.findViewById(R.id.player_detail_sightpoint);
            characterImage=itemView.findViewById(R.id.matching_character_detail_image);
            characterPosition=itemView.findViewById(R.id.matching_detail_position_info);
            Attribute1=itemView.findViewById(R.id.matching_position_info_detail_attribute1);
            Attribute2=itemView.findViewById(R.id.matching_position_info_detail_attribute2);
            Attribute3=itemView.findViewById(R.id.matching_position_info_detail_attribute3);
            item1=itemView.findViewById(R.id.matcing_detail_item1);
            item2=itemView.findViewById(R.id.matcing_detail_item2);
            item3=itemView.findViewById(R.id.matcing_detail_item3);
            item4=itemView.findViewById(R.id.matcing_detail_item4);
            item5=itemView.findViewById(R.id.matcing_detail_item5);
            item6=itemView.findViewById(R.id.matcing_detail_item6);
            item7=itemView.findViewById(R.id.matcing_detail_item7);
            item8=itemView.findViewById(R.id.matcing_detail_item8);
            item9=itemView.findViewById(R.id.matcing_detail_item9);
            item10=itemView.findViewById(R.id.matcing_detail_item10);
            item11=itemView.findViewById(R.id.matcing_detail_item11);
            item12=itemView.findViewById(R.id.matcing_detail_item12);
            item13=itemView.findViewById(R.id.matcing_detail_item13);
            item14=itemView.findViewById(R.id.matcing_detail_item14);
            item15=itemView.findViewById(R.id.matcing_detail_item15);
            item16=itemView.findViewById(R.id.matcing_detail_item16);
            nickname=itemView.findViewById(R.id.player_detail_nickname);
            itemid1=itemView.findViewById(R.id.itemid1);
            itemid2=itemView.findViewById(R.id.itemid2);
            itemid3=itemView.findViewById(R.id.itemid3);
            itemid4=itemView.findViewById(R.id.itemid4);
            itemid5=itemView.findViewById(R.id.itemid5);
            itemid6=itemView.findViewById(R.id.itemid6);
            itemid7=itemView.findViewById(R.id.itemid7);
            itemid8=itemView.findViewById(R.id.itemid8);
            itemid9=itemView.findViewById(R.id.itemid9);
            itemid10=itemView.findViewById(R.id.itemid10);
            itemid11=itemView.findViewById(R.id.itemid11);
            itemid12=itemView.findViewById(R.id.itemid12);
            itemid13=itemView.findViewById(R.id.itemid13);
            itemid14=itemView.findViewById(R.id.itemid14);
            itemid15=itemView.findViewById(R.id.itemid15);
            itemid16=itemView.findViewById(R.id.itemid16);
            mView=itemView;
        }
        void onBind(MatchingResultDetailModel data) {
            TAG.setText(data.getMatchingDetailTag());
            dealPoint.setText("입힌 피해량: " +data.getDealingDetailPoint());
            damagedPoint.setText("받은 피해량: "+data.getDamagedDetailPoint());
            killDeathAssist.setText(data.getKDADetail());
            kda.setText(" "+data.getKDAPOINTDetail());
            battlePoint.setText("전투 참여: " +data.getBattlePointDetail());
            sightPoint.setText("시야 점수: "+data.getSightPointDetail());
            Glide.with(itemView.getContext()).load(data.getMatchingDetailCharacterImage()).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.drawable.newbie).into(characterImage);
            if(data.getMatchingDetailCharacterPosition().contains("탱커"))
                characterPosition.setImageResource(R.drawable.tanker);
            else if(data.getMatchingDetailCharacterPosition().contains("원거리"))
                characterPosition.setImageResource(R.drawable.longdealer);
            else if(data.getMatchingDetailCharacterPosition().contains("근거리"))
                characterPosition.setImageResource(R.drawable.shortdealer);
            else if(data.getMatchingDetailCharacterPosition().contains("서"))
                characterPosition.setImageResource(R.drawable.support);
            else{
                characterPosition.setImageResource(R.drawable.newbie);
            }
           if(data.getMatchingResult().equals("win"))
                linearLayout.setBackgroundResource(R.color.blue_100);
            else
               linearLayout.setBackgroundResource(R.color.pink_100);
            characterNameLevel.setText(data.getCharacterDetailNameLevel());
            nickname.setText(data.getNickname());
            /* 특성 */
            Glide.with(itemView.getContext()).load(data.getMatchingDetailCharacterPositionAttribute1()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(Attribute1);
            Glide.with(itemView.getContext()).load(data.getMatchingDetailCharacterPositionAttribute2()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(Attribute2);
            Glide.with(itemView.getContext()).load(data.getMatchingDetailCharacterPositionAttribute3()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(Attribute3);
            /* 아이템 */
            Glide.with(itemView.getContext()).load("https://img-api.neople.co.kr/cy/items/"+data.getDetailItem1()).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.drawable.noitem).into(item1);
            Glide.with(itemView.getContext()).load("https://img-api.neople.co.kr/cy/items/"+data.getDetailItem2()).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.drawable.noitem).into(item2);
            Glide.with(itemView.getContext()).load("https://img-api.neople.co.kr/cy/items/"+data.getDetailItem3()).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.drawable.noitem).into(item3);
            Glide.with(itemView.getContext()).load("https://img-api.neople.co.kr/cy/items/"+data.getDetailItem4()).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.drawable.noitem).into(item4);
            Glide.with(itemView.getContext()).load("https://img-api.neople.co.kr/cy/items/"+data.getDetailItem5()).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.drawable.noitem).into(item5);
            Glide.with(itemView.getContext()).load("https://img-api.neople.co.kr/cy/items/"+data.getDetailItem6()).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.drawable.noitem).into(item6);
            Glide.with(itemView.getContext()).load("https://img-api.neople.co.kr/cy/items/"+data.getDetailItem7()).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.drawable.noitem).into(item7);
            Glide.with(itemView.getContext()).load("https://img-api.neople.co.kr/cy/items/"+data.getDetailItem8()).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.drawable.noitem).into(item8);
            Glide.with(itemView.getContext()).load("https://img-api.neople.co.kr/cy/items/"+data.getDetailItem9()).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.drawable.noitem).into(item9);
            Glide.with(itemView.getContext()).load("https://img-api.neople.co.kr/cy/items/"+data.getDetailItem10()).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.drawable.noitem).into(item10);
            Glide.with(itemView.getContext()).load("https://img-api.neople.co.kr/cy/items/"+data.getDetailItem11()).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.drawable.noitem).into(item11);
            Glide.with(itemView.getContext()).load("https://img-api.neople.co.kr/cy/items/"+data.getDetailItem12()).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.drawable.noitem).into(item12);
            Glide.with(itemView.getContext()).load("https://img-api.neople.co.kr/cy/items/"+data.getDetailItem13()).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.drawable.noitem).into(item13);
            Glide.with(itemView.getContext()).load("https://img-api.neople.co.kr/cy/items/"+data.getDetailItem14()).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.drawable.noitem).into(item14);
            Glide.with(itemView.getContext()).load("https://img-api.neople.co.kr/cy/items/"+data.getDetailItem15()).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.drawable.noitem).into(item15);
            Glide.with(itemView.getContext()).load("https://img-api.neople.co.kr/cy/items/"+data.getDetailItem16()).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.drawable.noitem).into(item16);
            itemid1.setText(data.getItemid1());
            itemid2.setText(data.getItemid2());
            itemid3.setText(data.getItemid3());
            itemid4.setText(data.getItemid4());
            itemid5.setText(data.getItemid5());
            itemid6.setText(data.getItemid6());
            itemid7.setText(data.getItemid7());
            itemid8.setText(data.getItemid8());
            itemid9.setText(data.getItemid9());
            itemid10.setText(data.getItemid10());
            itemid11.setText(data.getItemid11());
            itemid12.setText(data.getItemid12());
            itemid13.setText(data.getItemid13());
            itemid14.setText(data.getItemid14());
            itemid15.setText(data.getItemid15());
            itemid16.setText(data.getItemid16());


        }
    }
}
